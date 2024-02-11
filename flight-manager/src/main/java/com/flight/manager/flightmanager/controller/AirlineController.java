package com.flight.manager.flightmanager.controller;

import static com.flight.manager.flightmanager.model.Role.ROLE_ADMIN;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.AirlineService;

@Controller
public class AirlineController {
    
    private final AirlineService airlineService;

    AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

 
    @GetMapping(value = "addAirline")
    public String showAddForm(Model model) {
        model.addAttribute("airline", new AirlineDTO());
        return "add_airline"; // Thymeleaf file for adding an airline: add_airline.html
    }

    @PostMapping(value = "addAirline")
    public String addAirline(@ModelAttribute("airline") AirlineDTO airline, RedirectAttributes redirectAttributes) {
        AirlineDTO created = airlineService.createAirline(airline);
        redirectAttributes.addFlashAttribute("message", "Airline added successfully");
        return "redirect:/airline"; // Redirect to the airline list after addition
    }

    @GetMapping(value = "airline")
    public String getAirlines ( Authentication authentication , Model model){
        List<AirlineDTO> airlines = airlineService.getAllAirlines();
        boolean loggedIn = false;
        boolean isCrew = false;
        boolean isUser = false;
        boolean isAdmin = false;
        if (authentication != null && authentication.isAuthenticated()) {
            loggedIn = true;
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                // Assuming getRoles() returns a list of roles for the user
                Role role = user.getRole();
                if(role == Role.ROLE_CREW){
                    isCrew = true;
                    
                }else if (role == Role.ROLE_USER){
                    isUser =true;

                }else if (role == ROLE_ADMIN){
                    isAdmin = true;
                }
            }
        }
        model.addAttribute("airlines", airlines);

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("airlines", airlines);
        return "airline";
    }




    @PostMapping(value = "/deleteAirline/{id}")
    public String deleteAirline(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        airlineService.deleteAirline(id);
        redirectAttributes.addFlashAttribute("message", "Airline deleted successfully");
        return "redirect:/airline"; // Redirect to the airline list after deletion
    }
}

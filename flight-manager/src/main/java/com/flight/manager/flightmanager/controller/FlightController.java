package com.flight.manager.flightmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.manager.flightmanager.dto.AssignmentDTO;
import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.AssignmnetService;
import com.flight.manager.flightmanager.service.FlightService;
import com.flight.manager.flightmanager.service.ReservationService;
import com.flight.manager.flightmanager.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;
    
    

    FlightController(FlightService flightService, ReservationService reservationService ,
     AssignmnetService assignmnetService, UserService service){
        this.flightService = flightService;
    }


     @GetMapping("/flights")
    public String getFilteredFlights(Authentication authentication,
        @RequestParam(required = false) String departureDate,
                                     @RequestParam(required = false) String sourceAirportCode,
                                     @RequestParam(required = false) String destinationAirportCode,
                                     Model model) {
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

                }else if ( role == Role.ROLE_ADMIN){
                    isAdmin = true;
                }
            }
        }
        if (departureDate == null && sourceAirportCode == null ) {
            // No filters provided, get all flights
            model.addAttribute("flights", flightService.getAllFlights());
        } else {
          
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
    
             LocalDateTime dateTime = null;
        // Parse the string to LocalDateTime
        if(!departureDate.isBlank()){
        dateTime  = LocalDate.parse(departureDate).atTime(LocalTime.MIDNIGHT);
        }
            model.addAttribute("flights", flightService.getFilteredFlights(dateTime, sourceAirportCode , destinationAirportCode));
        }
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        return "flights";
    }
    

    @PostMapping(value = "deleteFlight/{flightId}")
    public String deleteFlight(@PathVariable Long flightId){
        flightService.deleteById(flightId);

         return "redirect:/flights";
    }

    @GetMapping("/createFlight")
    public String showCreateFlightForm(Model model) {
        model.addAttribute("flightDTO", new FlightDTO()); // Initialize an empty FlightDTO object
        return "create_flight"; // Return the Thymeleaf template for the create flight form
    }


    @PostMapping("/createFlight")
    public String showCreateFlightForm(@ModelAttribute("flightDTO") FlightDTO flightDTO) {
       
        flightService.addFlight(flightDTO);
        return "redirect:/flights";
    }


}

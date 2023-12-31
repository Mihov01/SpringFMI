package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.service.AirlineService;

@Controller
@RequestMapping(value = "/flight_managerTH")
public class AirlineControllerTH {
    
    private final AirlineService airlineService;

    AirlineControllerTH(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public String getAllAirlines(Model model) {
        List<AirlineDTO> airlines = airlineService.getAllAirlines();
        model.addAttribute("airlines", airlines);
        return "airlines"; // Thymeleaf file name: airlines.html
    }

    @GetMapping(value = "/add")
    public String showAddForm(Model model) {
        model.addAttribute("airline", new AirlineDTO());
        return "add_airline"; // Thymeleaf file for adding an airline: add_airline.html
    }

    @PostMapping(value = "/add")
    public String addAirline(@ModelAttribute("airline") AirlineDTO airline, RedirectAttributes redirectAttributes) {
        AirlineDTO created = airlineService.createAirline(airline);
        redirectAttributes.addFlashAttribute("message", "Airline added successfully");
        return "redirect:/flight_managerTH"; // Redirect to the airline list after addition
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AirlineDTO airline = airlineService.getById(id);
        model.addAttribute("airline", airline);
        return "edit_airline"; // Thymeleaf file for editing an airline: edit_airline.html
    }

    @PostMapping(value = "/edit")
    public String updateAirline(@ModelAttribute("airline") AirlineDTO airline, RedirectAttributes redirectAttributes) {
        AirlineDTO updated = airlineService.updateAirline(airline);
        redirectAttributes.addFlashAttribute("message", "Airline updated successfully");
        return "redirect:/flight_managerTH"; // Redirect to the airline list after update
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteAirline(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        airlineService.deleteAirline(id);
        redirectAttributes.addFlashAttribute("message", "Airline deleted successfully");
        return "redirect:/flight_managerTH"; // Redirect to the airline list after deletion
    }
}

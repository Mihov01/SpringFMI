package com.flight.manager.flightmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.service.FlightService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;


    FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public String showFlights(Model model) {
        List<FlightDTO> flights = flightService.getAllFlights(); // Retrieve flights from the service
        model.addAttribute("flights", flights); // Add flights to the model
        return "flights"; // Return the Thymeleaf template name
    }

    // Method to fetch flight data (Replace this with your actual data retrieval logic)
    private List<Flight> getFlightData() {
        // Simulated flight data generation
        List<Flight> flights = new ArrayList<>();
       
        // Add more flights or retrieve data from your database or service

        return flights;
    }
}

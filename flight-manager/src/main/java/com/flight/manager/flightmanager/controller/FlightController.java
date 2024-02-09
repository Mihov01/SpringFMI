package com.flight.manager.flightmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.FlightService;
import com.flight.manager.flightmanager.service.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;
    private final ReservationService reservationService;


    FlightController(FlightService flightService, ReservationService reservationService){
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    // Method to fetch flight data (Replace this with your actual data retrieval logic)
    private List<Flight> getFlightData() {
        // Simulated flight data generation
        List<Flight> flights = new ArrayList<>();
       
        // Add more flights or retrieve data from your database or service

        return flights;
    }


     @GetMapping("/flights")
    public String getFilteredFlights(@RequestParam(required = false) String departureDate,
                                     @RequestParam(required = false) String sourceAirportCode,
                                     @RequestParam(required = false) String destinationAirportCode,
                                     Model model) {
        if (departureDate == null && sourceAirportCode == null) {
            // No filters provided, get all flights
            model.addAttribute("flights", flightService.getAllFlights());
        } else {
          
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
    
             LocalDateTime dateTime = null;
        // Parse the string to LocalDateTime
        if(!departureDate.isBlank()){
            System.out.println("HUIIII");
        dateTime  = LocalDate.parse(departureDate).atTime(LocalTime.MIDNIGHT);
        }
            model.addAttribute("flights", flightService.getFilteredFlights(dateTime, sourceAirportCode , destinationAirportCode));
        }
        return "flights";
    }
    


    @PostMapping(value = "makeReservation/{flightNumber}")
    public String test(@PathVariable String flightNumber, Model model){


        System.out.println(flightNumber);
        model.addAttribute("flightNumber", flightNumber);
        return "payment";
    }


    @PostMapping(value = "reserve/{flightNumber}")
    public String makeReservation(@PathVariable String flightNumber , Model model){
        System.out.println(flightNumber);
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         if (authentication != null && authentication.getPrincipal() instanceof User) {
            // Get the UserDetails object which contains user details
            User user = (User) authentication.getPrincipal();
            reservationService.makeReservation(user, flightNumber);
         }
         return "redirect:/reservations";
         
    }

    @GetMapping(value="reservations")
    public String getReservations(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
           // Get the UserDetails object which contains user details
           User user = (User) authentication.getPrincipal();
           List<Flight> flights = reservationService.getrReservations(user);
           model.addAttribute("flights", flights);
        }
       
        return "reservations";
    }
}

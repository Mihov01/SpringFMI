package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.ReservationService;


@Controller
public class ReservationsController {
    

    private final ReservationService reservationService;


    ReservationsController(ReservationService reservationService){
        this.reservationService = reservationService;
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
    public String getReservations(Authentication authentication ,Model model){
        

        boolean loggedIn = false;
        boolean isCrew = false;
        boolean isUser = false;
        boolean isAdmin = false;
        if (authentication != null && authentication.isAuthenticated()) {
            loggedIn = true;
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                Role role = user.getRole();
                if(role == Role.ROLE_CREW){
                    isCrew = true;
                    
                }else if (role == Role.ROLE_USER){
                    isUser =true;

                }else if ( role == Role.ROLE_ADMIN){
                    isAdmin = true;
                }
            }
            User user = (User) authentication.getPrincipal();
            List<Flight> flights = reservationService.getrReservations(user);
            model.addAttribute("flights", flights);
        }
         
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        
        return "reservations";
    }


}

package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;

@Controller
public class HomeController {

    @GetMapping(value = "home")
    public String getHome(Authentication authentication,  Model model) {
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

                }else if (role == Role.ROLE_ADMIN){
                    isAdmin = true;
                }
            }
        }
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        return "home"; 
    }

    
}

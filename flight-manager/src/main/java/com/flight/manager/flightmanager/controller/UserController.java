package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.UserService;

@Controller
public class UserController {
    

    private final UserService service;

    UserController(UserService service){
        this.service = service;
    }

    
    @GetMapping(value = "users")
    public String getUsers(Authentication authentication ,Model model){
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
       
       
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        List<User> us= service.getAllUsers();
        model.addAttribute("allUsers", us); 
        return "users";
    }


    @PostMapping(value = "users/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId){
        service.delete(userId);
        return "redirect:/users";
    }
    

    @GetMapping("updateUser/{userId}")
    public String showUpdateUserPage(@PathVariable Long userId, Model model) {
        User user = service.getUserById(userId).get(); // Implement this method in your UserService
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("updateUser")
    public String showUpdateUserPage(@ModelAttribute("user") User user , Model model) {
        service.update(user);
        return "redirect:/users";
    }
}

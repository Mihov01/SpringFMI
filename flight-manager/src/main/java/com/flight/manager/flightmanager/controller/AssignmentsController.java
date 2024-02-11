package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.flight.manager.flightmanager.dto.AssignmentDTO;
import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.AssignmnetService;

@Controller
public class AssignmentsController {
    

    private final AssignmnetService assignmnetService;

    AssignmentsController(AssignmnetService assignmnetService){
        this.assignmnetService = assignmnetService;
    }

        @GetMapping(value="/assignments")
    public String getAssignments(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
             // Get the UserDetails object which contains user details
           User user = (User) authentication.getPrincipal();
           List<AssignmentDTO> flights = assignmnetService.getAssinments(user);
           model.addAttribute("flights", flights);
        }
       
       
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        return "assignments";
    }



    @GetMapping(value="/allAssignments")
    public String getAllAssignments(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
            
           List<AssignmentDTO> flights = assignmnetService.getAllAssinments();
           System.out.println(flights.size());
           model.addAttribute("assignments", flights);
        }
       
       
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isCrew", isCrew);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        return "allAssignments";
    }



    @PostMapping(value =  "/deleteAssignment/{userName}/{flightNumber}")
    public String deleteAssignment(@PathVariable String userName , @PathVariable String flightNumber){

        assignmnetService.deleteAssignment(userName, flightNumber);
        return "redirect:/allAssignments";
    }


    @GetMapping("/addAssignment")
    public String showAddAssignment(Model model) {
        model.addAttribute("assignmentDTO", new AssignmentDTO()); // Initialize an empty FlightDTO object
        return "addAssignment"; // Return the Thymeleaf template for the create flight form
    }

    
    @PostMapping("/saveAssignment")
    public String saveAssignment(Authentication authentication , @ModelAttribute("assignmentDTO") AssignmentDTO assignmentDTO) {
       
        Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
        assignmnetService.saveAssignment(user, assignmentDTO);
            }
        return "redirect:/allAssignments";
    }
}

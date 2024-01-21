package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PutMapping
    ResponseEntity<User> updateUser( @RequestBody User user){
        System.out.println("HUUUUUUIIIII");
        User updated = userService.update(user);

        return ResponseEntity.ok().body(updated);
    }

    @GetMapping
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/update")
    User updatePerm (@RequestBody User user){
        return userService.updatePermissions(user);
    }
    
}

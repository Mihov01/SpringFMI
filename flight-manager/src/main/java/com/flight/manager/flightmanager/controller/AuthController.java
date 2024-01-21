package com.flight.manager.flightmanager.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flight.manager.flightmanager.config.JwtUtils;
import com.flight.manager.flightmanager.dto.CredentialsDTO;
import com.flight.manager.flightmanager.dto.LoginResponceDTO;
import com.flight.manager.flightmanager.exception.InvalidEntityDataException;
import com.flight.manager.flightmanager.model.Role;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.service.UserService;

import jakarta.validation.Valid;

import static com.flight.manager.flightmanager.utils.ErrorHandlingUtils.handleValidationErrors;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "register" )
    public ResponseEntity<User> registerReader(@Valid @RequestBody User user, Errors errors) {
        handleValidationErrors(errors);
        if (!user.getRole().equals(Role.ROLE_USER)) {
            throw new InvalidEntityDataException(
                    String.format("Role: '%s' is invalid. You can self-register only in 'User' role.",
                            user.getRole()));
        }
        User created = userService.create(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(created.getId())
        ).body(created);
    }

    @PostMapping("login")
    public LoginResponceDTO login(@Valid @RequestBody CredentialsDTO credentials, Errors errors) {
        handleValidationErrors(errors);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword()
        ));
        final User user = userService.getUserByUsername(credentials.getUsername());
        final String token = jwtUtils.generateToken(user);
        return new LoginResponceDTO(token, user);
    }
}













package com.bank.homework1.controller;

import com.bank.homework1.model.User;
import com.bank.homework1.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth-register"; // Assuming you have a view named auth-register for registration form
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("Error registering user: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            return "redirect:/auth/register"; // Redirect back to registration form if validation fails
        }

        authService.register(user); // Call your registration service method here
        return "redirect:/auth/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "auth-login"; // Assuming you have a view named auth-login for login form
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {
        User loggedInUser = authService.login(user.getUsername(), user.getPassword());

        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/auth/login"; // Redirect back to login page if login fails
        }

        session.setAttribute("loggedInUser", loggedInUser); // Set user in session upon successful login
        return "redirect:/dashboard"; // Redirect to dashboard or any other page after successful login
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate(); // Invalidate session upon logout
        return "redirect:/"; // Redirect to home page after logout
    }
}

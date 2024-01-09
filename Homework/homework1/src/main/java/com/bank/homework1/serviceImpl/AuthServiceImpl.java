package com.bank.homework1.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.homework1.model.Role;
import com.bank.homework1.model.User;
import com.bank.homework1.service.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User register(User user) {
        if (user.getRoles().contains(Role.ADMIN)) {
            throw new InvalidEntityException("Admins cannot self-register.");
        }
        return userService.createUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}

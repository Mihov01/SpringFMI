package com.bank.homework1.service;

import com.bank.homework1.model.User;

public interface AuthService {
    User register(User user);
    User login(String username, String password);
}
package com.bank.homework1.service;

import java.util.List;

import com.bank.homework1.model.User;

public interface UserService {
    List<User> getUsers();
    User createUser(User user);
    User updateUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User deleteUser(Long id);
    long getUsersCount();
    List<User> createUsersBatch(List<User> users);
}
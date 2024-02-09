package com.flight.manager.flightmanager.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.model.User;

@Service
public interface UserService {

     Optional<User> getUserByUsername(String Username);
     User create(User user);
     User update(User user);
     List<User> getAllUsers();
     User updatePermissions(User user);

    
}

package com.flight.manager.flightmanager.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.model.User;

@Service
public interface UserService {

     User getUserByUsername(String Username);
     User create(User user);
     User update(User user);
     List<User> getAllUsers();
     User updatePermissions(User user);

    
}

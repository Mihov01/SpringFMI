package com.flight.manager.flightmanager.service;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.model.User;

@Service
public interface UserService {

     User getUserByUsername(String Username);
     User create(User user);

    
}

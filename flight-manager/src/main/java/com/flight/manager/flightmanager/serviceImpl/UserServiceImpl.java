package com.flight.manager.flightmanager.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.exception.InvalidEntityDataException;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.repository.UserRepo;
import com.flight.manager.flightmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    private final UserRepo repo;

    UserServiceImpl(UserRepo repo){
        this.repo = repo;
    }

    @Override
    public User getUserByUsername(String Username){
        
        return repo.findByUsername(Username).get();
    }



     @Override
    public User create(User user) throws InvalidEntityDataException{
        user.setId(null);
        if(repo.findByUsername(user.getUsername()).isPresent()) {
            throw new InvalidEntityDataException(
                    String.format("User with username '%s' already exists", user.getUsername()));
        }
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}

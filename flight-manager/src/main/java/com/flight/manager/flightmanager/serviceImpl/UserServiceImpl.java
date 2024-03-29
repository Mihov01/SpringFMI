package com.flight.manager.flightmanager.serviceImpl;

import static com.flight.manager.flightmanager.model.Role.ROLE_ADMIN;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.exception.InvalidEntityDataException;
import com.flight.manager.flightmanager.exception.UnautorizedException;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.repository.UserRepo;
import com.flight.manager.flightmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepo repo , PasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserByUsername(String Username){
        
       
        return repo.findByUsername(Username);
        
    
    }



     @Override
    public User create(User user) throws InvalidEntityDataException{
        user.setId(null);
        if(repo.findByUsername(user.getUsername()).isPresent()) {
            throw new InvalidEntityDataException(
                    String.format("User with username '%s' already exists", user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User update (User user){
        Optional<User>  userToUpadte = repo.findByUsername(user.getUsername());
        if (userToUpadte.isEmpty()){
            throw new InvalidEntityDataException("No user with such username ");
        } 

        User user1 = userToUpadte.get();

        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setRole(user.getRole());
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());

        repo.save(user1);
        return user1;
    }

   

    @Override
    public List<User> getAllUsers(){


return repo.findAll();
}


@Override
public void delete(Long id){

     repo.deleteById(id);
}

@Override
public  Optional<User> getUserById(Long id){
    return repo.findById(id);
}
}

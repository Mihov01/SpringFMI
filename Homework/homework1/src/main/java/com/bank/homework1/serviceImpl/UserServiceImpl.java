package com.bank.homework1.serviceImpl;


;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bank.homework1.model.User;
import com.bank.homework1.service.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(User user) {
        userRepo.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new InvalidEntityException(String.format("User with username '%s' already exists.", user.getUsername()));
        });
        user.setCreated(new Date());
        user.setModified(new Date());
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            user.setRoles(Set.of(Role.SELLER));
        }
        user.setActive(true);
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User old = getUserById(user.getId());
        if (user.getUsername() != null && !user.getUsername().equals(old.getUsername())) {
            throw new InvalidEntityException("Username of a user could not be changed.");
        }
        user.setModified(new Date());
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException(String.format("User '%s' not found.", username)));
    }

    @Override
    public User deleteUser(Long id) {
        User old = userRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
        userRepo.deleteById(id);
        return old;
    }

    @Override
    public long getUsersCount() {
        return userRepo.count();
    }

    @Override
    @Transactional
    public List<User> createUsersBatch(List<User> users) {
        return users.stream()
                .map(this::createUser)
                .collect(Collectors.toList());
    }
}

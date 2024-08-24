package com.scm.services.impl;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
//        user id have tp generate dynamically
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        //TODO: password encode : user.setPassword(userId);


        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found!"));
        // user ko user2 se update kr denge
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setProfileLink(user.getProfileLink());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProviderUserId(user.getProviderUserId());
        user2.setProviders(user.getProviders());

        // Save the user entity (user2) to the database using the userRepo repository.
        // The save() method will either insert a new user or update an existing one, depending on the user's state.
        User save = userRepo.save(user2);

        // Return the saved user wrapped in an Optional.
        // If the user is saved successfully, the Optional will contain the user object;
        // if the user is null, the Optional will be empty (null-safe return).
        return Optional.of(save);
    }

    @Override
    public void deleteUser(String id) {
        // fetched the user
        User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found!"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        // fetched the user
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user2 = userRepo.findById(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}

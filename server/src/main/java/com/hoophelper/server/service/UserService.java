package com.hoophelper.server.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoophelper.server.model.User;
import com.hoophelper.server.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    
}

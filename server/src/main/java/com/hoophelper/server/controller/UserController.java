package com.hoophelper.server.controller;
import com.hoophelper.server.model.User;
import com.hoophelper.server.repository.UserRepository;
import com.hoophelper.server.service.UserService;
import com.hoophelper.server.model.LoginRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    UserService userService;

    // GET: Get user
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }

    // POST: Login user
    // * Configure with spring security later *
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest request) { // Map for json output
        String username = request.getUsername();
        String password = request.getPassword();

        for (User u : tempDB) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of("status", "success"));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", "unauthorized"));
    }

    // POST: Register user
    // * Configure with spring security later *
    /* 
    @PostMapping("/signup")
    public ResponseEntity<User> setUser(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
    } 
    */

}

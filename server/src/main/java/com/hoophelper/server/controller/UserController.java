package com.hoophelper.server.controller;
import com.hoophelper.server.model.User;
import com.hoophelper.server.model.LoginRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    // In memory list (for now)
    private List<User> tempDB = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    // GET: Get user
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        if (id < 0 || id >= tempDB.size())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tempDB.get(id));
    }

    // POST: Login user
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
    @PostMapping("/signup")
    public ResponseEntity<User> setUser(@RequestBody User user) {
        // Check for duplicate users 
        for (User u : tempDB) {
            if (u.getUsername().equals(user.getUsername())) 
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Build returns just the code
        }

        // Set ID
        user.setId(idCounter.getAndIncrement());
        // **Add password hashing here?**
        tempDB.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // GET: Get user's games?
}

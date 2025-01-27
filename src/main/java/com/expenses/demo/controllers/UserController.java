package com.expenses.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.demo.entities.User;
import com.expenses.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }


    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String email){
        Optional<User> user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Hello World");
    }
    
}

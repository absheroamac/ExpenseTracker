package com.expenses.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses.demo.entities.User;
import com.expenses.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }
    
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}

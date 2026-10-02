package com.thiyagarasan.studenthub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thiyagarasan.studenthub.entity.User;
import com.thiyagarasan.studenthub.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String test() {
        return "Backend Working";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        User foundUser = userRepository.findByUsernameAndPassword(
                user.getUsername(),
                user.getPassword());

        System.out.println("Found User: " + foundUser);

        return foundUser;
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

}
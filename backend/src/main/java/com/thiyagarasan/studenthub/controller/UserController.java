package com.thiyagarasan.studenthub.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.thiyagarasan.studenthub.dto.RegisterRequest;
import com.thiyagarasan.studenthub.dto.LoginRequest;
import com.thiyagarasan.studenthub.dto.AuthResponse;

import com.thiyagarasan.studenthub.entity.Student;
import com.thiyagarasan.studenthub.entity.User;

import com.thiyagarasan.studenthub.repository.UserRepository;
import com.thiyagarasan.studenthub.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
            UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        Student student = userService.register(request);

        return new AuthResponse(
                "Registration successful",
                student.getUser().getId(),
                student.getId(),
                student.getUser().getUsername(),
                student.getName(),
                student.getEmail());
    }

    @GetMapping("/id/{id}")
    public Map<String, Object> getStudentById(@PathVariable int id) {

        Student student = userService.getStudentById(id);

        return Map.of(
                "id", student.getId(),
                "name", student.getName(),
                "email", student.getEmail());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        if (request.getUsername() == null ||
                request.getPassword() == null ||
                request.getUsername().isBlank() ||
                request.getPassword().isBlank()) {

            throw new RuntimeException("Username and password are required");
        }

        String username = request.getUsername().trim();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!userService.checkPassword(
                request.getPassword(), user.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        Student student = userService.getStudentByUserId(user.getId());

        return new AuthResponse(
                "Login successful",
                user.getId(),
                student.getId(),
                user.getUsername(),
                student.getName(),
                student.getEmail());
    }
}
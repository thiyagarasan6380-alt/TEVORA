
package com.thiyagarasan.studenthub.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.thiyagarasan.studenthub.entity.User;
import com.thiyagarasan.studenthub.entity.Student;
import com.thiyagarasan.studenthub.repository.UserRepository;
import com.thiyagarasan.studenthub.repository.StudentRepository;
import com.thiyagarasan.studenthub.dto.RegisterRequest;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository,
            StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student register(RegisterRequest request) {

        String username = request.getUsername().trim();
        String name = request.getName().trim();
        String email = request.getEmail().trim();
        String password = request.getPassword();

        if (username.isEmpty() || name.isEmpty()
                || email.isEmpty() || password == null
                || password.isBlank()) {
            throw new RuntimeException("All fields are required");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (studentRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("STUDENT");

        userRepository.save(user);

        Student student = new Student();
        student.setUser(user);
        student.setName(name);
        student.setEmail(email);

        return studentRepository.save(student);
    }

    public Student getStudentByUserId(int userId) {
        return studentRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public boolean checkPassword(
            String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(
                rawPassword, encodedPassword);
    }
}

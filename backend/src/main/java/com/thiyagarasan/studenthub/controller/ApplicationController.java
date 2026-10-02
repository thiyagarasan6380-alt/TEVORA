package com.thiyagarasan.studenthub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thiyagarasan.studenthub.entity.Application;
import com.thiyagarasan.studenthub.repository.ApplicationRepository;

import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:5173")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping
    public Application apply(@RequestBody Application application) {
        application.setStatus("Pending");
        return applicationRepository.save(application);
    }

    @GetMapping("/project/{projectId}")
    public List<Application> getApplicationsByProject(
            @PathVariable int projectId) {

        return applicationRepository.findByProjectId(projectId);
    }

    @PutMapping("/accept/{id}")
    public Application acceptApplicant(@PathVariable int id) {

        Application application = applicationRepository.findById(id).orElse(null);

        if (application != null) {
            application.setStatus("Accepted");
            return applicationRepository.save(application);
        }

        return null;
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @GetMapping("/accepted")
    public List<Application> getAcceptedApplications() {
        return applicationRepository.findByStatus("Accepted");
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getStudentApplications(
            @PathVariable int studentId) {

        return applicationRepository.findByStudentId(studentId);
    }

}
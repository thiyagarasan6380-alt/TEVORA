
package com.thiyagarasan.studenthub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.thiyagarasan.studenthub.dto.ApplicationResponse;
import com.thiyagarasan.studenthub.service.ApplicationService;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Apply to a project
    @PostMapping
    public ApplicationResponse apply(
            @RequestParam int studentId,
            @RequestParam int projectId) {

        return applicationService.apply(studentId, projectId);
    }

    // View all applications
    @GetMapping
    public List<ApplicationResponse> getAllApplications() {

        return applicationService.getAllApplications();
    }

    // View applications for a project
    @GetMapping("/project/{projectId}")
    public List<ApplicationResponse> getProjectApplications(
            @PathVariable int projectId) {

        return applicationService.getProjectApplications(projectId);
    }

    // View applications submitted by a student
    @GetMapping("/student/{studentId}")
    public List<ApplicationResponse> getStudentApplications(
            @PathVariable int studentId) {

        return applicationService.getStudentApplications(studentId);
    }

    // Accept an application
    @PutMapping("/accept/{id}")
    public ApplicationResponse acceptApplication(
            @PathVariable int id,
            @RequestParam int ownerId) {

        return applicationService.acceptApplication(id, ownerId);
    }
}

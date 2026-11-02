
package com.thiyagarasan.studenthub.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiyagarasan.studenthub.dto.ApplicationResponse;
import com.thiyagarasan.studenthub.entity.*;
import com.thiyagarasan.studenthub.repository.*;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            ProjectRepository projectRepository,
            TeamRepository teamRepository,
            TeamService teamService) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    // Convert entity into DTO
    private ApplicationResponse convertToResponse(Application app) {

        return new ApplicationResponse(
                app.getId(),
                app.getStudent().getId(),
                app.getStudent().getName(),
                app.getProject().getId(),
                app.getProject().getProjectName(),
                app.getStatus(),
                app.getAppliedAt());
    }

    // Apply to a project
    @Transactional
    public ApplicationResponse apply(int studentId, int projectId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!"OPEN".equalsIgnoreCase(project.getStatus())) {
            throw new RuntimeException("Project applications are closed");
        }

        if (project.getOwner().getId() == studentId) {
            throw new RuntimeException("You cannot apply to your own project");
        }

        boolean alreadyApplied = applicationRepository
                .findByStudent_Id(studentId)
                .stream()
                .anyMatch(app -> app.getProject().getId() == projectId);

        if (alreadyApplied) {
            throw new RuntimeException("You have already applied to this project");
        }

        Application application = new Application();
        application.setStudent(student);
        application.setProject(project);
        application.setStatus("PENDING");

        return convertToResponse(
                applicationRepository.save(application));
    }

    // View all applications
    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // View applications for a project
    public List<ApplicationResponse> getProjectApplications(int projectId) {

        return applicationRepository.findByProject_Id(projectId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // View applications submitted by a student
    public List<ApplicationResponse> getStudentApplications(int studentId) {

        return applicationRepository.findByStudent_Id(studentId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Accept applicant and automatically add to team
    @Transactional
    public ApplicationResponse acceptApplication(
            int applicationId, int ownerId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (application.getProject().getOwner().getId() != ownerId) {
            throw new RuntimeException(
                    "Only the project owner can accept applicants");
        }

        if (!"OPEN".equalsIgnoreCase(application.getProject().getStatus())) {
            throw new RuntimeException("Project applications are closed");
        }

        if (!"PENDING".equalsIgnoreCase(application.getStatus())) {
            throw new RuntimeException("Application has already been processed");
        }

        // Change application status
        application.setStatus("ACCEPTED");

        // Save immediately so TeamService can verify ACCEPTED status
        applicationRepository.saveAndFlush(application);

        int projectId = application.getProject().getId();
        int studentId = application.getStudent().getId();

        // Find existing team or create a new one
        Team team = teamRepository.findByProject_Id(projectId)
                .orElse(null);

        if (team == null) {
            teamService.createTeam(projectId, ownerId);

            team = teamRepository.findByProject_Id(projectId)
                    .orElseThrow(() -> new RuntimeException(
                            "Unable to create project team"));
        }

        // Add accepted applicant to the team
        teamService.addMember(team.getId(), studentId);

        return convertToResponse(application);
    }
}

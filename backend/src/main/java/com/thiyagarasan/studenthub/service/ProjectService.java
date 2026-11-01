
package com.thiyagarasan.studenthub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thiyagarasan.studenthub.dto.ProjectRequest;
import com.thiyagarasan.studenthub.dto.ProjectResponse;
import com.thiyagarasan.studenthub.entity.Project;
import com.thiyagarasan.studenthub.entity.Student;
import com.thiyagarasan.studenthub.repository.ProjectRepository;
import com.thiyagarasan.studenthub.repository.StudentRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    // Get all projects
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Create project
    public ProjectResponse createProject(ProjectRequest request) {

        Student owner = studentRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        return convertToResponse(projectRepository.save(project));
    }

    // Update project
    public ProjectResponse updateProject(
            int projectId, ProjectRequest request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (project.getOwner().getId() != request.getOwnerId()) {
            throw new RuntimeException("You are not the project owner");
        }

        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());

        return convertToResponse(projectRepository.save(project));
    }

    // Delete project
    public void deleteProject(int projectId, int ownerId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (project.getOwner().getId() != ownerId) {
            throw new RuntimeException("You are not the project owner");
        }

        projectRepository.delete(project);
    }

    // Search projects by skill
    public List<ProjectResponse> searchProjectsBySkill(String skillName) {
        return projectRepository.findProjectsBySkill(skillName)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Update project status
    public ProjectResponse updateProjectStatus(
            int projectId, int ownerId, String status) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (project.getOwner().getId() != ownerId) {
            throw new RuntimeException("You are not the project owner");
        }

        if (!status.equalsIgnoreCase("OPEN")
                && !status.equalsIgnoreCase("CLOSED")) {
            throw new RuntimeException("Invalid project status");
        }

        project.setStatus(status.toUpperCase());

        return convertToResponse(projectRepository.save(project));
    }

    // Convert entity to DTO
    private ProjectResponse convertToResponse(Project project) {

        return new ProjectResponse(
                project.getId(),
                project.getProjectName(),
                project.getDescription(),
                project.getStatus(),
                project.getOwner().getId(),
                project.getOwner().getName(),
                project.getCreatedAt());
    }
}

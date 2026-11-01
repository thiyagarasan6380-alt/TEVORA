
package com.thiyagarasan.studenthub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.thiyagarasan.studenthub.dto.ProjectRequest;
import com.thiyagarasan.studenthub.dto.ProjectResponse;
import com.thiyagarasan.studenthub.service.ProjectService;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ProjectResponse createProject(
            @RequestBody ProjectRequest request) {
        return projectService.createProject(request);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateProject(
            @PathVariable int id,
            @RequestBody ProjectRequest request) {
        return projectService.updateProject(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(
            @PathVariable int id,
            @RequestParam int ownerId) {

        projectService.deleteProject(id, ownerId);
        return "Project deleted successfully";
    }

    @GetMapping("/search")
    public List<ProjectResponse> searchProjects(
            @RequestParam String skill) {

        return projectService.searchProjectsBySkill(skill);
    }

    @PutMapping("/{id}/status")
    public ProjectResponse updateProjectStatus(
            @PathVariable int id,
            @RequestParam int ownerId,
            @RequestParam String status) {

        return projectService.updateProjectStatus(
                id, ownerId, status);
    }
}

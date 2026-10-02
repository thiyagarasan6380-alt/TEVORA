package com.thiyagarasan.studenthub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiyagarasan.studenthub.entity.Project;
import com.thiyagarasan.studenthub.repository.ProjectRepository;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable int id,
            @RequestBody Project updatedProject) {

        Project project = projectRepository.findById(id).orElse(null);

        if (project != null) {
            project.setProjectName(updatedProject.getProjectName());
            project.setDescription(updatedProject.getDescription());
            project.setStatus(updatedProject.getStatus());
            project.setOwner(updatedProject.getOwner());
            project.setSkillName(updatedProject.getSkillName());

            return projectRepository.save(project);
        }

        return null;
    }
}
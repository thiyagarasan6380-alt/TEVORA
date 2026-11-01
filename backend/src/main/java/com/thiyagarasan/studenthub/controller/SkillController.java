
package com.thiyagarasan.studenthub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.thiyagarasan.studenthub.dto.SkillRequest;
import com.thiyagarasan.studenthub.dto.SkillResponse;
import com.thiyagarasan.studenthub.dto.SkillAssignmentRequest;
import com.thiyagarasan.studenthub.service.SkillService;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Create a skill
    @PostMapping
    public SkillResponse createSkill(
            @RequestBody SkillRequest request) {

        return skillService.createSkill(request);
    }

    // Get all skills
    @GetMapping
    public List<SkillResponse> getAllSkills() {

        return skillService.getAllSkills();
    }

    // Assign skills to a student
    @PostMapping("/student/{studentId}")
    public String assignStudentSkills(
            @PathVariable int studentId,
            @RequestBody SkillAssignmentRequest request) {

        return skillService.assignStudentSkills(
                studentId, request.getSkillIds());
    }

    // Assign required skills to a project
    @PostMapping("/project/{projectId}")
    public String assignProjectSkills(
            @PathVariable int projectId,
            @RequestParam int ownerId,
            @RequestBody SkillAssignmentRequest request) {

        return skillService.assignProjectSkills(
                projectId,
                ownerId,
                request.getSkillIds());
    }

    @GetMapping("/student/{studentId}")
    public List<SkillResponse> getStudentSkills(
            @PathVariable int studentId) {

        return skillService.getStudentSkills(studentId);
    }

    @GetMapping("/project/{projectId}")
    public List<SkillResponse> getProjectSkills(
            @PathVariable int projectId) {

        return skillService.getProjectSkills(projectId);
    }
}

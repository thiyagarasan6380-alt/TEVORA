
package com.thiyagarasan.studenthub.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiyagarasan.studenthub.dto.SkillRequest;
import com.thiyagarasan.studenthub.dto.SkillResponse;
import com.thiyagarasan.studenthub.entity.*;
import com.thiyagarasan.studenthub.repository.*;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    private final StudentSkillRepository studentSkillRepository;
    private final ProjectSkillRepository projectSkillRepository;

    public SkillService(
            SkillRepository skillRepository,
            StudentRepository studentRepository,
            ProjectRepository projectRepository,
            StudentSkillRepository studentSkillRepository,
            ProjectSkillRepository projectSkillRepository) {

        this.skillRepository = skillRepository;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
        this.studentSkillRepository = studentSkillRepository;
        this.projectSkillRepository = projectSkillRepository;
    }

    // Create a new skill
    public SkillResponse createSkill(SkillRequest request) {

        String name = request.getSkillName().trim();

        if (name.isEmpty()) {
            throw new RuntimeException("Skill name cannot be empty");
        }

        if (skillRepository.findBySkillName(name).isPresent()) {
            throw new RuntimeException("Skill already exists");
        }

        Skill skill = new Skill();
        skill.setSkillName(name);

        Skill saved = skillRepository.save(skill);

        return new SkillResponse(saved.getId(), saved.getSkillName());
    }

    // Display all available skills
    public List<SkillResponse> getAllSkills() {

        return skillRepository.findAll()
                .stream()
                .map(skill -> new SkillResponse(
                        skill.getId(),
                        skill.getSkillName()))
                .toList();
    }

    // Assign skills to a student
    @Transactional
    public String assignStudentSkills(int studentId, List<Integer> skillIds) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        for (int skillId : skillIds) {

            Skill skill = skillRepository.findById(skillId)
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            StudentSkillId id = new StudentSkillId();
            id.setStudentId(studentId);
            id.setSkillId(skillId);

            if (!studentSkillRepository.existsById(id)) {

                StudentSkill studentSkill = new StudentSkill();
                studentSkill.setId(id);
                studentSkill.setStudent(student);
                studentSkill.setSkill(skill);

                studentSkillRepository.save(studentSkill);
            }
        }

        return "Student skills assigned successfully";
    }

    // Assign required skills to a project
    @Transactional
    public String assignProjectSkills(
            int projectId, int ownerId, List<Integer> skillIds) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Check project ownership
        if (project.getOwner().getId() != ownerId) {
            throw new RuntimeException(
                    "Only the project owner can assign skills");
        }

        for (int skillId : skillIds) {

            Skill skill = skillRepository.findById(skillId)
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            ProjectSkillId id = new ProjectSkillId();
            id.setProjectId(projectId);
            id.setSkillId(skillId);

            if (!projectSkillRepository.existsById(id)) {

                ProjectSkill projectSkill = new ProjectSkill();
                projectSkill.setId(id);
                projectSkill.setProject(project);
                projectSkill.setSkill(skill);

                projectSkillRepository.save(projectSkill);
            }
        }

        return "Project skills assigned successfully";
    }

    // Get skills assigned to a student
    public List<SkillResponse> getStudentSkills(int studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found");
        }

        return studentSkillRepository.findByStudent_Id(studentId)
                .stream()
                .map(ss -> new SkillResponse(
                        ss.getSkill().getId(),
                        ss.getSkill().getSkillName()))
                .toList();
    }

    public List<SkillResponse> getProjectSkills(int projectId) {

        if (!projectRepository.existsById(projectId)) {
            throw new RuntimeException("Project not found");
        }

        return projectSkillRepository.findByProject_Id(projectId)
                .stream()
                .map(ps -> new SkillResponse(
                        ps.getSkill().getId(),
                        ps.getSkill().getSkillName()))
                .toList();
    }
}

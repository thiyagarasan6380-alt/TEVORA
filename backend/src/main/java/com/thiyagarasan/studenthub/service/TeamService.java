
package com.thiyagarasan.studenthub.service;

import java.util.List;

import com.thiyagarasan.studenthub.dto.TeamResponse;
import com.thiyagarasan.studenthub.dto.TeamMemberResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiyagarasan.studenthub.entity.*;
import com.thiyagarasan.studenthub.repository.*;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final ApplicationRepository applicationRepository;

    public TeamService(
            TeamRepository teamRepository,
            ProjectRepository projectRepository,
            StudentRepository studentRepository,
            TeamMemberRepository teamMemberRepository,
            ApplicationRepository applicationRepository) {

        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.applicationRepository = applicationRepository;
    }

    // Create one team for a project
    @Transactional
    public TeamResponse createTeam(int projectId, int ownerId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (project.getOwner().getId() != ownerId) {
            throw new RuntimeException("Only the project owner can create a team");
        }

        if (teamRepository.findByProject_Id(projectId).isPresent()) {
            throw new RuntimeException("Team already exists");
        }

        Team team = new Team();
        team.setProject(project);

        return convertTeam(teamRepository.save(team));
    }

    // Add an accepted student to a team
    @Transactional
    public TeamMember addMember(int teamId, int studentId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        int projectId = team.getProject().getId();

        // Check whether the student was accepted for this project
        boolean accepted = applicationRepository
                .findByProject_Id(projectId)
                .stream()
                .anyMatch(app -> app.getStudent().getId() == studentId &&
                        "ACCEPTED".equalsIgnoreCase(app.getStatus()));

        if (!accepted) {
            throw new RuntimeException(
                    "Only accepted applicants can join this team");
        }

        // Prevent duplicate team membership
        boolean alreadyMember = teamMemberRepository
                .findByTeam_Id(teamId)
                .stream()
                .anyMatch(member -> member.getStudent().getId() == studentId);

        if (alreadyMember) {
            throw new RuntimeException(
                    "Student is already a team member");
        }

        TeamMember member = new TeamMember();
        member.setTeam(team);
        member.setStudent(student);

        return teamMemberRepository.save(member);
    }

    // View team members
    public List<TeamMember> getTeamMembers(int teamId) {

        if (!teamRepository.existsById(teamId)) {
            throw new RuntimeException("Team not found");
        }

        return teamMemberRepository.findByTeam_Id(teamId);
    }

    private TeamResponse convertTeam(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getProject().getId(),
                team.getProject().getProjectName());
    }

    private TeamMemberResponse convertMember(TeamMember member) {
        return new TeamMemberResponse(
                member.getId(),
                member.getStudent().getId(),
                member.getStudent().getName(),
                member.getJoinedAt());
    }
}

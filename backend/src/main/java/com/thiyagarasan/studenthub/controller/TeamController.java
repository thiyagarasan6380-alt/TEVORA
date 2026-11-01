
package com.thiyagarasan.studenthub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.thiyagarasan.studenthub.dto.TeamResponse;
import com.thiyagarasan.studenthub.dto.TeamMemberResponse;

import com.thiyagarasan.studenthub.entity.Team;
import com.thiyagarasan.studenthub.entity.TeamMember;
import com.thiyagarasan.studenthub.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "http://localhost:5173")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // Create a team for a project
    @PostMapping("/create/{projectId}")
    public TeamResponse createTeam(
            @PathVariable int projectId,
            @RequestParam int ownerId) {

        return teamService.createTeam(projectId, ownerId);
    }

    // Add an accepted student to the team
    @PostMapping("/{teamId}/members/{studentId}")
    public TeamMember addMember(
            @PathVariable int teamId,
            @PathVariable int studentId) {

        return teamService.addMember(teamId, studentId);
    }

    // View team members
    @GetMapping("/{teamId}/members")
    public List<TeamMember> getTeamMembers(
            @PathVariable int teamId) {

        return teamService.getTeamMembers(teamId);
    }
}

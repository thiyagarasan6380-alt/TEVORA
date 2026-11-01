package com.thiyagarasan.studenthub.repository;

import com.thiyagarasan.studenthub.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamMemberRepository
        extends JpaRepository<TeamMember, Integer> {

    List<TeamMember> findByTeam_Id(int teamId);

    List<TeamMember> findByStudent_Id(int studentId);
}
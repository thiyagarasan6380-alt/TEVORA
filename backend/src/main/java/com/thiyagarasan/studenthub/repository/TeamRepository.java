package com.thiyagarasan.studenthub.repository;

import com.thiyagarasan.studenthub.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findByProject_Id(int projectId);
}
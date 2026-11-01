package com.thiyagarasan.studenthub.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.thiyagarasan.studenthub.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("""
                SELECT DISTINCT p
                FROM Project p
                JOIN ProjectSkill ps ON ps.project.id = p.id
                WHERE LOWER(ps.skill.skillName) = LOWER(:skillName)
            """)
    List<Project> findProjectsBySkill(
            @Param("skillName") String skillName);
}

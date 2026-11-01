package com.thiyagarasan.studenthub.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thiyagarasan.studenthub.entity.ProjectSkill;
import com.thiyagarasan.studenthub.entity.ProjectSkillId;

public interface ProjectSkillRepository
        extends JpaRepository<ProjectSkill, ProjectSkillId> {

    List<ProjectSkill> findByProject_Id(int projectId);
}
package com.thiyagarasan.studenthub.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thiyagarasan.studenthub.entity.StudentSkill;
import com.thiyagarasan.studenthub.entity.StudentSkillId;

public interface StudentSkillRepository
        extends JpaRepository<StudentSkill, StudentSkillId> {

    List<StudentSkill> findByStudent_Id(int studentId);
}
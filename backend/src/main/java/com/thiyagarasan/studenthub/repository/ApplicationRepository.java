package com.thiyagarasan.studenthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.thiyagarasan.studenthub.entity.Application;

public interface ApplicationRepository
        extends JpaRepository<Application, Integer> {

    List<Application> findByProjectId(int projectId);

    List<Application> findByStatus(String status);

    List<Application> findByStudentId(int studentId);

}
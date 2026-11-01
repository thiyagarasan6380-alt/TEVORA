package com.thiyagarasan.studenthub.repository;

import com.thiyagarasan.studenthub.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Integer> {

    List<Application> findByProject_Id(int projectId);

    List<Application> findByStudent_Id(int studentId);

    List<Application> findByStatus(String status);
}
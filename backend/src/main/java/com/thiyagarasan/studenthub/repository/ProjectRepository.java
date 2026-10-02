package com.thiyagarasan.studenthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thiyagarasan.studenthub.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
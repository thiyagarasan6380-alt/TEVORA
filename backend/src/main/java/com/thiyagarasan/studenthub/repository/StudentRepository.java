package com.thiyagarasan.studenthub.repository;

import com.thiyagarasan.studenthub.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByUser_Id(int userId);

    Optional<Student> findByEmail(String email);
}
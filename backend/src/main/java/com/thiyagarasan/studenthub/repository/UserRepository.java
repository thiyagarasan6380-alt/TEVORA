package com.thiyagarasan.studenthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thiyagarasan.studenthub.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
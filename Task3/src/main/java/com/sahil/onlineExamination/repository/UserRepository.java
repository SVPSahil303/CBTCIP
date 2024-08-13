package com.sahil.onlineExamination.repository;

import com.sahil.onlineExamination.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

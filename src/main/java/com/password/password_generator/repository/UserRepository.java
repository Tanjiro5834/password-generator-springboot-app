package com.password.password_generator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.password.password_generator.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String findByUsername(String username);
    User findByEmail(String email);
}

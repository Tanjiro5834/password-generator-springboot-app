package com.password.password_generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.password.password_generator.entity.Password;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long>{
    
}

package com.example.ch502jwtbasedplainlogin.repository;

import com.example.ch502jwtbasedplainlogin.domain.User;
import com.example.ch502jwtbasedplainlogin.dto.SignUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // TODO
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
package com.captainyun7.ch503sessionbasedspringsecuritylogin.controller;

import com.captainyun7.ch503sessionbasedspringsecuritylogin.dto.UserResponse;
import com.captainyun7.ch503sessionbasedspringsecuritylogin.service.AuthService;
import com.captainyun7.ch503sessionbasedspringsecuritylogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final AuthService authService;
    
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')") // 이 메서드를 호출하려면 role 이 USER 이어야함
    // 메서드로 접근권한 제한 가능
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(UserResponse.fromEntity(authService.getCurrentUser()));
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
} 
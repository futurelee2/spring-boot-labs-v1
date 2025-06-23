package com.example.ch502jwtbasedplainlogin.dto;

import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    // password 주면 안됨!
    private Long id;
    private String username;
    private String email;
    private String role;
    
    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
} 
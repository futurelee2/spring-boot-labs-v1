package com.example.ch502jwtbasedplainlogin.config;

import com.example.ch502jwtbasedplainlogin.domain.User;
import com.example.ch502jwtbasedplainlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 초기 데이터

@Component // 빈등록 -> 자동으로 실행됨
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        // 테스트용 관리자 계정 생성

        User admin = User.builder()
                .username("admin")
                .password("admin123")
                .email("admin@example.com")
                .role("ADMIN")
                .build();
        userRepository.save(admin);


        // 테스트용 일반 사용자 계정 생성

        User user1 = User.builder()
                .username("user1")
                .password("user123")
                .email("user1@example.com")
                .role("USER")
                .build();
        userRepository.save(user1);
        User user2 = User.builder()
                .username("user2")
                .password("user123")
                .email("user@example.com")
                .role("USER")
                .build();
        userRepository.save(user2);
        User user3 = User.builder()
                .username("user3")
                .password("user123")
                .email("user3@example.com")
                .role("USER")
                .build();
        userRepository.save(user3);

    }
} 
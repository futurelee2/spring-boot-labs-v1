package com.example.ch502jwtbasedplainlogin.service;

import com.captainyun7.ch501sessionbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import com.captainyun7.ch501sessionbasedplainlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public UserResponse createUser(SignUpRequest signUpRequest) {
        // TODO
        // DB 로 저장
        User user = User.builder()
                        .username(signUpRequest.getUsername())
                        .password(signUpRequest.getPassword()) // 실제로는 암호화 해서 넣기
                        .email(signUpRequest.getEmail())
                        .role("USER")
                        .build();

        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + id));
    }

    public Optional<User> getUserByUsername(String username) {
        // TODO
        return userRepository.findByUsername(username); // 이 함수를 호출한 곳에서 null 체크를 이미 했기때문에 Optional 이지만 null 체크 안해도 도미
    }


    public boolean existsByUsername(String username) {
        // TODO
        return userRepository.existsByUsername(username);
    }

     public boolean existsByEmail(String email) {
         // TODO
        return userRepository.existsByEmail(email);

     }
} 
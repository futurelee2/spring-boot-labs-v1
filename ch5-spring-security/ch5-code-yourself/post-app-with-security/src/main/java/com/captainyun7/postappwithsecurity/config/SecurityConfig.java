package com.captainyun7.postappwithsecurity.config;

import com.captainyun7.postappwithsecurity.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // 아래 코드 대신 @RequiredArgsConstructor 로 대체
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않아서 STATELESS로 두기
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**", "/h2-console/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // 위 path가 아니면 필터 검증? 필요
                )
                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // username~~ 앞에 필터 추가
        // TODO: Spring Security의 필터 체인을 설정합니다.
        // 1. 세션 관리를 STATELESS로 설정하여 세션을 사용하지 않도록 합니다 (`sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))`).
        // 2. 요청별 인가 규칙을 설정합니다.
        //    - `/api/auth/**`, `/h2-console/**` 등 특정 경로는 모두에게 허용 (`permitAll`).
        //    - `/api/admin/**` 경로는 'ADMIN' 역할을 가진 사용자에게만 허용 (`hasRole("ADMIN")`).
        //    - 그 외의 모든 요청은 인증된 사용자에게만 허용 (`anyRequest().authenticated()`).
        // 3. `JwtAuthenticationFilter`를 `UsernamePasswordAuthenticationFilter` 앞에 추가합니다 (`addFilterBefore`).
        http.headers(
                headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
} 
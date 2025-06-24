package com.captainyun7.ch503sessionbasedspringsecuritylogin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 시큐리티 6.X 버전 사용
// 제일 중요한 설정 객체임

@Configuration
@EnableWebSecurity // 설정할 때 사용 
public class SecurityConfig {

    @Bean // 빈 등록
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // TODO: [1] URL별 접근 권한을 설정합니다.
                .authorizeHttpRequests(auth -> auth
                        // 퍼블릭path 지정
                        .requestMatchers("/api/auth/**").permitAll()
                        // 역할에 따른 구분 (admin/ users..)
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // 인증된 누구나 허용하겠다.
                        .anyRequest().authenticated()
                )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())


            // TODO: [2] 세션 관리 설정을 합니다. => 이걸 통해서 디버깅했을 때 authority 정보가 제대로 잘 나오게 되었음
            .sessionManagement(session -> session.sessionCreationPolicy(
                    SessionCreationPolicy.IF_REQUIRED)
                    .maximumSessions(1)
                    .expiredUrl("/api/auth/login")) // 세션이 만료되면 어디 경로로? 로그인 페이지로
                .securityContext(securityContext ->
                    securityContext.requireExplicitSave(false) // 세션 저장을 직접 할래? false = 너가 해줘
                );


        // H2 콘솔 사용을 위한 설정
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    // TODO: [1] PasswordEncoder를 Bean으로 등록합니다.
    // 비밀번호 암호화 하는 것
    // 사용자 : user123 -> DB 저장 시, 암호화함
    @Bean
    public PasswordEncoder passwordEncoder() { //PasswordEncoder 는 인터페이스임
        // 빈주입할때 객체를 직접 만들어줌
        return new BCryptPasswordEncoder(); // 구현체임. 이 알고리즘을 사용함
        
    }
    
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
} 
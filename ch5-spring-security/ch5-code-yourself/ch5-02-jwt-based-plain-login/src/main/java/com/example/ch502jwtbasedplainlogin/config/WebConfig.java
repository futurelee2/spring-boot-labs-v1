package com.example.ch502jwtbasedplainlogin.config;

import com.example.ch502jwtbasedplainlogin.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Sprint MVC 설정하는 곳
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    // TODO
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/**") //  실행
                .excludePathPatterns("/api/auth/**"); // 실행 X
    }


}
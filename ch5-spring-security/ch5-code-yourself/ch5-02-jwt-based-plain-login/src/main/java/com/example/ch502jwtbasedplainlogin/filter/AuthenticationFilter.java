package com.example.ch502jwtbasedplainlogin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component // 스프링부트 안에 있기때문에 컴포넌트 등록 필요
@Order(1)
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {
    
    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/api/auth/login",
            "/api/auth/register",
            "/h2-console"
    );
    
    private final ObjectMapper objectMapper;
    
    // 필터는 스프링 영향권 밖이기때문에 자동으로 JSON 변환이 안됨으로 직접 수동으로 해줘야함
    @Override
    // 요청받을 때 http 메시지를 톰캣이 파싱해서 request 객체 안에 들어있음 (@RequestBody 처럼)
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO : 필터 로직 작성
        // 권한에 따른 인가
        // 강제 형변환 필요(부모 -> 자식)
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // request안에 path 정보 들어있음
        String path = httpRequest.getRequestURI();

        // 로그인하지 않았어도 공개 경로는 접근 가능
        // 로그인하지 않았을 때 요청한 path가  퍼블릭path이면 인가하겠다.
        if(isPublicPath(path)){
            // 필터 성공 시, 다음 스탬으로 넘김 (dispathsevlet ?)
            chain.doFilter(request, response);
        }

        // 사용자 정보를 검증
        HttpSession session = httpRequest.getSession(false); // 세션 정보를 가져올수있음
        
        // 세션이 없거나, 사용자 정보가 세션에 없으면 검증 실패
        if(session == null || session.getAttribute(USER_SESSION_KEY) == null){
            // 인증 실패했다고 클라이언트에 응답
            // 응답객체를 직접 만들어서 응답해줘야 가능함
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json"); // json 으로 응답하기 위함

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "인증이 필요합니다.");
            errorResponse.put("status", "error");

            // 소켓이랑 연결되어있어 파일 출력할수있음
            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return;
        }

        chain.doFilter(request, response);

    }
    
    private boolean isPublicPath(String path) {
        // 뭐라도 매칭되는게 있으면 True 반환
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
} 
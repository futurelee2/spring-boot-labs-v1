package com.captainyun7.postappwithsecurity.filter;

import com.captainyun7.postappwithsecurity.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // TODO: JWT 인증 필터 로직을 구현합니다.
        // 1. 요청 헤더(Authorization)에서 'Bearer ' 접두사를 제외한 JWT를 추출합니다.
        // 2. JWT가 존재하고 유효한 경우, 토큰에서 사용자 이름을 추출합니다.
        // 3. SecurityContext에 인증 정보가 없는 경우, UserDetailsService에서 사용자 정보를 로드합니다.
        // 4. 토큰이 유효한지 검증합니다(JwtUtil.validateToken).
        // 5. 유효한 토큰인 경우, UsernamePasswordAuthenticationToken을 생성하고 사용자의 상세 정보(WebAuthenticationDetailsSource)를 설정합니다.
        // 6. SecurityContextHolder에 인증 정보를 설정합니다. => 이걸로 검증이 되었나 안되었나로 판단하기때문에 이 규칙으로 알려주기
        
        
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(7); // Bearer 떼고 토큰만 가져오기
        String username = null;
        try{
            username = jwtUtil.getUsernameFromToken(jwt);
        } catch(ExpiredJwtException e){

        }

        // 유효한 사용자면 SecurityContext에 인증 정보를 수동으로 넣어줌
        if (username != null &&  SecurityContextHolder.getContext().getAuthentication() == null) {
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        filterChain.doFilter(request, response); //  다음으로 넘어감
    }
} 
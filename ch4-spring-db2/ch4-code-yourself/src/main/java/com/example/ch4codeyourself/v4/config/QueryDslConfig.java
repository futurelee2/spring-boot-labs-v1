package com.example.ch4codeyourself.v4.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {

    // jpa 가 컨테이너를 만들어줌 >??
    // jpa 에서  사용하는 녀석(순수 jpa 에서 사용하는 것) -> 원래 얘로 조작하나, 추상화로 이때까지 사용한적은 없었음
    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}

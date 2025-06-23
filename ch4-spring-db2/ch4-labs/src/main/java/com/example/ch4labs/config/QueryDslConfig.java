package com.example.ch4labs.config;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정 클래스 (내부의 Bean 메서드들이 스프링 빈으로 등록됨)
@RequiredArgsConstructor // EntityManager 를 주입받기 위한 생성자 자동 등록
public class QueryDslConfig {

    // JPA 의 핵심 객체를 주입받음
    // DB와 상호작용할 때 사용하는 핵심 객체
    // jpa 가 컨테이너를 만들어줌 >??
    // jpa 에서  사용하는 녀석(순수 jpa 에서 사용하는 것) -> 원래 얘로 조작하나, 추상화로 이때까지 사용한적은 없었음
    private final EntityManager entityManager;

    @Bean
    //JPAQueryFactory: QueryDSL의 쿼리 작성 도우미
    // JPAQueryFactory는 내부적으로 EntitiyManger를 사용해서 DB 쿼리를 실행함
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}

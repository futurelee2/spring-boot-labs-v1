package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 수동 설정 => 빈을 정의할 수 있음.
// 스프링 설정 클래스를 통해 수동을 빈 등록하는 방법
// 스프링 설정 클래스임을 나타내는 메타 데이터
// 내부적으로 @Component 를 포함하므로, 컴포넌트 스캔 대상이 됨

@Configuration
public class BeanConfig {
    // 메서드가 스프링 빈을 반환한다는 메타데이터
    // 메서드의 반환값이 스프링 컴테이너에 빈으로 등록됨
    // 빈의 이름은 기본적으로 메서드 명과 동일
    // 즉, 이 메서드가 반환하는 객체를 빈으로 등록해줘!! 이 뜻
    @Bean
    public HelloConfigBean helloConfigBean() {
        return new HelloConfigBean();
    }
}

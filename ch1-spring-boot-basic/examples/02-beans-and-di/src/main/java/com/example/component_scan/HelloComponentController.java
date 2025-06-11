package com.example.component_scan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 웹 요청을 처리하는 컨트롤러! 라고 알려주는 메타데이터. => 빈 등록 + 웹 컨트롤러 역할
@RestController
public class HelloComponentController {
    
    // 불변성. final로 선언 가능
    // 테스트 용이. mock 객체 주입하기 쉬움
    private final HelloComponentBean component;

    // 생성자를 통해 의존성 주입 받는 방식
    public HelloComponentController(HelloComponentBean component) {
        this.component = component;
    }

    @GetMapping("/hello-component")
    public String hello() {
        return component.sayHello();
    }
}

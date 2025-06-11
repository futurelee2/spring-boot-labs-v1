package com.example.component_scan;

import org.springframework.stereotype.Component;

// 다른 곳에서 주입받아 사용될 객체
@Component
public class HelloComponentBean {
    public String sayHello() {
        return "Hello from @Component";
    }
}
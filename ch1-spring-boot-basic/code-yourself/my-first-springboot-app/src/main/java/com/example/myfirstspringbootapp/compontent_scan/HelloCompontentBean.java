package com.example.myfirstspringbootapp.compontent_scan;

import org.springframework.stereotype.Component;

//@Component
//public class HelloComponentBean {
//    public String sayHello(){
//        return "Hello from @Component";
//    }
//}

// 컴포넌트들을 스캔해서 빈으로 만듦 -> 위 코드는 빈이 됨. -> 스프링이 관리하는 객체가 빈 자체가 객체임
// 즉 new 로 생성안해줘도 됨
// SpringBootApplication 내부에 들어가면 컴포넌트 스캔 잇음
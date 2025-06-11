//package com.example.myfirstspringbootapp.compontent_scan;

import com.example.component_scan.HelloComponentBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class HelloComponentController {
//    private final HelloComponentBean component;
//    // 자바에서 클래스는 new 를 사용해서 만듦. new 키워드 없음. 어떻게 실행이 되느냐? 컴포넌트로 등록을 해서. 이걸 빈이라고 함
//
//    public HelloComponentController(HelloCompontentBean component){
//        this.component = component;
//    }
//    @GetMapping("/hello-component")
//    //이 path 요청이 오면 이 함수로 매핑이된다?
//    public String hello(){
//        return component.sayHello();
//    }
//}

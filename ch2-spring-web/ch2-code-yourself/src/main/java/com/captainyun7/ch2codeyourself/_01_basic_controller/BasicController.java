package com.captainyun7.ch2codeyourself._01_basic_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 컴포넌트 스캔에 의해 빈이 됨. 안에 컴포넌트 들어가있음.
// 이 페이지 전체  json 방식 : 데이터이자 api 방식


@Controller
@ResponseBody // 응답을 body 에 담겠다 -> http body에 담아줘
// ResponseBody 호출시 HttpMessageConverter로 가서 데이터 포맷을 변환해줌
public class BasicController {
    
    // GET basic/hello 요청이 왔을 때, hello 메서드 실행
    @GetMapping("/basic/hello")
    public String hello(){
        return "hello";// 문자열이 아님, 뷰를 찾고 있는 것. (보여지는 html 만드는 것 = 뷰)
    
    }
    // userId가 동적인 값 = {경로변수}라고 부름
    @GetMapping("/basic/users/{userId}")
    @ResponseBody
    public String users(@PathVariable int userId){
        // 만약 요청이 basic/users/3 -> userId = 3 으로 바인딩 됨
        return "user Id" + userId;
    }
    @GetMapping("/basic/users/{userId}/orders/{ordersId}")
    @ResponseBody
    public String userOrder(@PathVariable int userId, @PathVariable int ordersId){
        // 만약 요청이 basic/users/3 -> userId = 3 으로 바인딩 됨
        return "user Id :" + userId + "orders Id :" + ordersId;
    }

    // /basic/params?name=1&age=2 데이터가 이렇게 왔을 때.
    @ResponseBody
    @GetMapping("basic/params") 
    public String params(@RequestParam String name, @RequestParam int age){ // @RequestParam 생략가능함
        return "name" + name + "age" + age; // 키: value 로 들어감
    }

    @ResponseBody
    @GetMapping("basic/filter")
    public String params(@RequestParam Map<String, String> params){
        return "전체파라미터" + params; ///basic/filter?type=new&mirae=new
    }

    @PostMapping("basic/users")
    @ResponseBody
    public String post2(){ // 포스트맨 post 로 요청
        return "사용자 생성 !!! ";
    }
    @PostMapping("basic/users/{userId}") // 하나 수정
    @ResponseBody
    public String put(){ // 포스트맨 post 로 요청
        return "사용자 수정 성공 !!! ";
    }

    @DeleteMapping("basic/users/{userId}") // 하나 수정
    @ResponseBody
    public String delete(){ // 포스트맨 post 로 요청
        return "사용자 삭제 성공 !!! ";
    }
}

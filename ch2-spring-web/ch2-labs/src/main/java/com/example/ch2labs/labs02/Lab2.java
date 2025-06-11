package com.example.ch2labs.labs02;

//### 주사위 굴리기 API
//
//- **GET** `/dice`
//        - 서버는 1~6 사이의 숫자 중 랜덤값 반환
//
//```json
//{ "dice": 5 }

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
public class Lab2 {
    @GetMapping("/dice")
    public Map<String,Integer> lab(){
        int dice = (int)(Math.random()*6)+1;
        return Map.of("dice",dice); // 스프링에서 자동으로 json으로 직렬화해줌
    }


}

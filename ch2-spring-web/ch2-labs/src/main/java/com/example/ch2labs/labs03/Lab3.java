package com.example.ch2labs.labs03;

//### 랜덤 번호 생성기 API
//
//- **GET** `/random?min=1&max=100`
//        - 지정된 범위 내에서 랜덤 정수 하나를 생성해 JSON으로 응답
//
//```json
//{ "number": 42 }


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
public class Lab3 {
    @GetMapping("/random")
    public Map<String,Integer> lab(@RequestParam int min, @RequestParam int max){
        int random = (int)(Math.random()*(max-min+1)) + min;
//        return "number:" + random;
        return  Map.of("random",random);
    }
}

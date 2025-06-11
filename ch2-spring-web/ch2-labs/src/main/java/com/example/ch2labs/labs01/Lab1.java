package com.example.ch2labs.labs01;


//- **GET** `/calc?x=10&y=3&op=mul`
//        - Query 파라미터로 두 수와 연산자(op)를 받아 계산 결과 반환
//
//| op 값 | 의미 |
//        |-------|------|
//        | add   | 더하기 |
//        | sub   | 빼기 |
//        | mul   | 곱하기 |
//        | div   | 나누기 |
//
//        - 응답 예시: `"결과: 10 * 3 = 30"`

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class Lab1 {
    @GetMapping("/calc")
     public String cal(@RequestParam int x, @RequestParam int y , @RequestParam String op) {
         if(op.equals("add")) {
             return "결과 :" + x + "" + y+ "=" + (x+y);
         } else if (op.equals("sub")) {
             return "결과 :" + x + "-" + y+ "=" + (x-y);

         }else if (op.equals("mul")) {
             return "결과 :" + x + "*" + y+ "=" + (x*y);
         }else{
             return "결과 :" + x + "/" + y+ "=" + (x/y);

         }
     }
}

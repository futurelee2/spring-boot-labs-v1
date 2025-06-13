package com.example.ch2labs.labs05;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
public class Lab5 {
    @GetMapping("/sum-digits")
    public ResponseEntity<?> sum(@RequestParam String number){
        //  검증 먼저 후, 에러 호출

        // 파라미터 누락
        if(number == null || number.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("number 파라미터는 필수입니다."));
        };
        
        // 음수인경우
        if (number.startsWith("-")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("음수는 지원하지 않습니다. 양의 정수를 입력해주세요."));
        }

        // 정수 아님
        number.split();




    @GetMapping("/422")
    public ResponseEntity<String> get422(){
        // 정수 아닌 값 입력 시  `422 Unprocessable Entity`
            "error": "정수만 입력 가능합니다. 예: /sum-digits?number=1234"
        return ResponseEntity.status(HttpStatus.OK).body("200");
    }


    }


}

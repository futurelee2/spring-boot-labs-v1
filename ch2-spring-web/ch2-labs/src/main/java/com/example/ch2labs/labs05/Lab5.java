package com.example.ch2labs.labs05;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
public class Lab5 {
    @GetMapping("/sum-digits")
    public ResponseEntity<?> sum(@RequestParam String number) {
        //  검증 먼저 후, 에러 호출

        // 파라미터 누락
        if (number == null || number.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("number 파라미터는 필수입니다."));
        }
        ;

        // 음수인경우
        if (number.startsWith("-")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("음수는 지원하지 않습니다. 양의 정수를 입력해주세요."));
        }

        // 정수 or 정수 아님
        try{
            String[] nums = number.split("");
            int sum= 0;
            for (int i = 0 ; i < nums.length; i ++) {
                sum+=Integer.parseInt(nums[i]);
            }
            return ResponseEntity.ok(Map.of("각 자리 숫자 합:" , sum));
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("정수만 입력 가능합니다."));
        }

    }
}

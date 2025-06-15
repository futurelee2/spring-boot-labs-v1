package com.example.ch2labs.labs06;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//#### 요청 바디
//
//```json
//{
//    "words": ["apple", "banana", "kiwi"]
//}
//```
//
//        ```json
//{
//    "message": "가장 길게는 단어는 'banana'입니다."
//}
//```
//
//        #### 상태 코드:
//
//        * 입력 범위가 빈가 같은 경우 `400 Bad Request`
//        * 필요 필드 누르기 경우 `400 Bad Request`
//        * 단어 내역이 무한 경우 `422 Unprocessable Entity`

@RestController
@RequestMapping("/longest-word")
public class Lab6 {
    @PostMapping
    public ResponseEntity<String> findLongWord(@RequestBody LongWordRequset request) {

        List<String> words = request.getWords();

        if (words==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("words 필드가 존재하지 않습니다.");
        }

        if(words.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("단어 목록이 없습니다.");
        }

        if(words.size() > 1000){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("단어 목록을 1,000개 이하로 입력하세요.");
        }

        List<String> sorted = words.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());

        return ResponseEntity.ok("가장 긴 단어는 " + sorted.get(0) + "입니다.");

    }
}

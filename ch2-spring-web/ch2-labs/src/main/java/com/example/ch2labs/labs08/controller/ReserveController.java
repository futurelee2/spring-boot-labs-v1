package com.example.ch2labs.labs08.controller;

import com.example.ch2labs.labs08.Service.ReserveService;
import com.example.ch2labs.labs08.dto.CreateResponse;
import com.example.ch2labs.labs08.dto.ReserveResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;


    @PostMapping
    public ResponseEntity<Map<String, String>> reserveCreate(@RequestBody @Valid CreateResponse response) {
         reserveService.create(response);
         return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", response.getUserId()+ "님의 예약이 완료되었습니다."));
    }

}

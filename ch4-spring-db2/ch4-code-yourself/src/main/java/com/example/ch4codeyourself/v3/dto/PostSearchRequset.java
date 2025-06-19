package com.example.ch4codeyourself.v3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequset {
    // MyBatis 에서? "" 안주면 에러가 나서 줬었음. "" 빼면 null 값임

    private String keyword;
    // 추가
    private String author;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    //
    private String sort;
    private int page = 0; // jpa 는 페이지가 0번 인덱스부터 시작함.
    private int size = 10;
}

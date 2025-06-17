package com.example.ch4codeyourself.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequset {
    private String keyword = "";
    private int page = 0; // jpa 는 페이지가 0번 인덱스부터 시작함.
    private int size = 10;
}

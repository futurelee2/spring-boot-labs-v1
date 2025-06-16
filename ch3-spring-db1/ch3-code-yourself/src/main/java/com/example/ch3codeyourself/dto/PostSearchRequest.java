package com.example.ch3codeyourself.dto;

import lombok.Data;

@Data
public class PostSearchRequest {
    private String keyword = ""; //  null 로 주면 정상적 초기화 안됨. 빈문자열 줘야함.
    private int page = 1; // 객체 인스턴스 만들때, 값을 안주면 null 로 초기화. 값 주면 기본값
    private int size = 10;

    // getter 로 읽음 -> offset으로 xml에서 불러올 수 있음
    private int getOffset(){
        return (page - 1)*size;
    }
}

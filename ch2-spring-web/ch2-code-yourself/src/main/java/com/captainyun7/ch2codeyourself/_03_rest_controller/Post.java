package com.captainyun7.ch2codeyourself._03_rest_controller;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // arugment 없는 기본 생성자
//@AllArgsConstructor // 모든 전 필드에 붙여줌?
public class Post {
    private String title;
    private Long id;
    private String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}

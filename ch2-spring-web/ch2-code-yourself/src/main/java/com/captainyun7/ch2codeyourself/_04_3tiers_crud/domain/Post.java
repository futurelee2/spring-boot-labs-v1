package com.captainyun7.ch2codeyourself._04_3tiers_crud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // 요청 응답을 처리하기 위해 열어줌?
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String body;
//
//    // All
//    public Post(Long id, String title, String body) {
//        this.id = id;
//        this.title = title;
//        this.body = body;
//    }
//
//    //No
//    public Post() {
//    }
    
    // 만약 위에 값중 한개가 final일때 Require 쓰면 final 하나만 초기화

}

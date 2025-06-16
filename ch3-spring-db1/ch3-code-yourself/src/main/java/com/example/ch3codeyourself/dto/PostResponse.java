package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String body;

    // Post -> PostResponse
    public static PostResponse from(Post post){
        // static 붙이는 이유는 객체 생성하지 않고도 클래스 이름으로 접근 가능.
        return new PostResponse(post.getId(), post.getTitle(), post.getBody());
    }
}

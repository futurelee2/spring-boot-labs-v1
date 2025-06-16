package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본생성자 없어서 에러나면 넣어주기
public class PostCreateRequest {
    private String title;
    private String body;

    // 여기에서 처리 시, 응집도가 좋아짐
    public Post toDomain(){
        Post post = new Post();
        post.setTitle(this.title);
        post.setBody(this.body);
        return post;
    };

}

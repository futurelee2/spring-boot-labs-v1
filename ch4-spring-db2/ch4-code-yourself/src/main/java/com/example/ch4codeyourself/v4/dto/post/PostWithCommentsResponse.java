package com.example.ch4codeyourself.v4.dto.post;

import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostWithCommentsResponse {
    private PostResponse post;
    private List<CommentResponse> comments;

    public static PostWithCommentsResponse from(Post post){
        return PostWithCommentsResponse.builder()
                .post(PostResponse.from(post))
                .comments(post.getComments().stream().map(CommentResponse::from).collect(Collectors.toList())) // 이전까지 코멘트 안들고오다가 이때 코멘트를 가져와서 쿼리를 내보냄
                .build();
    }
}

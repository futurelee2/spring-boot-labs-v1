package com.example.ch3codeyourself.service;

import com.example.ch3codeyourself.domain.Post;
import com.example.ch3codeyourself.dto.PostCreateRequest;
import com.example.ch3codeyourself.dto.PostResponse;
import com.example.ch3codeyourself.dto.PostSearchRequest;
import com.example.ch3codeyourself.dto.UpdateRequest;
import com.example.ch3codeyourself.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    // MyBatis 에서는 repository => mapper 라고 부름
    private final PostMapper postMapper;

    public PostResponse createPost(PostCreateRequest request) {
        // 서비스 -> 레포지토리 넘길때는 도메인을 넘기는것이 안전
        // PostCreateRequest -> Post 로 변환
        // request가 들어오면 객체. => 메서드 호출 가능
         Post post = request.toDomain();
         postMapper.save(post);

         // 정상적으로 저장되면 post 로 확인가능
        // Post -> PostResponse 변환
        return PostResponse.from(post);
    }

    public List<PostResponse> getPosts() {
        return postMapper.findAll().stream()
                .map(post -> PostResponse.from(post))
                .toList();

    }

    public PostResponse getPost(Long id) {
        return PostResponse.from(postMapper.findOne(id));
    }


    public PostResponse updatePost(UpdateRequest response, Long id) {
        Post post = postMapper.findOne(id);
        post.setTitle(response.getTitle());
        post.setBody(response.getBody());
        postMapper.update(post);
        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        postMapper.delete(id);

    }

    public List<PostResponse> searchPosts(PostSearchRequest search) {
        // 검색은 db에서 필터해서 보여줘야하는 부분이라서 service에서 할 부분 없음.
        List<PostResponse> posts = postMapper.findAllWithSearch(search).stream()
                .map(post-> PostResponse.from(post))
                .toList();
        return posts;
    }
}

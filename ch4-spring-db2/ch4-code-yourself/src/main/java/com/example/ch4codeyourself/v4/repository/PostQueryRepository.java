package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.dto.post.PostSearchRequset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

// QueryDSL
public interface PostQueryRepository{
    // 기존 service에서  if else 문 조회 방식
    Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable);
    // repository에서 한번에 처리 하는 방법 (dto 프로젝션- dto로 바로 변환해주는 방식)
    Page<PostResponse> search(PostSearchRequset requset);
}


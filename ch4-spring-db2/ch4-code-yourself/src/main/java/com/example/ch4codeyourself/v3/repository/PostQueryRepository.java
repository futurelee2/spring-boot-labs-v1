package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

// QueryDSL
public interface PostQueryRepository {
    Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable);
}


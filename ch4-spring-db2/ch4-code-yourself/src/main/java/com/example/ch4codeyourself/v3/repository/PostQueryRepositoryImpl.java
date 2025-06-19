package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import com.example.ch4codeyourself.v3.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable) {

        QPost post = QPost.post;
        List<Post> content = queryFactory // queryFactory =  쿼리 공장 = 쿼리 만들어줌
                .selectFrom(post) // 테이블이 아닌 객체
                .where(post.createdAt.goe(createdAt)) // greater or equal
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(post.count()).from(post)
                .where(post.createdAt.goe(createdAt)) // greater or equal
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();;

        return new PageImpl<>(content, pageable, count != null?count:0);
    }

}

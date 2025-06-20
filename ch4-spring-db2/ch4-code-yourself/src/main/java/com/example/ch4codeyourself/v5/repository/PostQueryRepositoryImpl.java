package com.example.ch4codeyourself.v5.repository;

import com.example.ch4codeyourself.v5.domain.QPost;
import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.post.PostResponse;
import com.example.ch4codeyourself.v5.dto.post.PostSearchRequset;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

// QueryDSL
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
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null?count:0);

    }
    
    @Override
    public Page<PostResponse> search(PostSearchRequset requset) {
        // QueryDSL 로 동적 쿼리 만들기
        QPost post = QPost.post;

        //BooleanBuilder ( 흐름만 알기. 쿼리dsl 사용한다)
        BooleanBuilder builder = new BooleanBuilder(); // 빌더 패턴이 존재함 -> 체이닝 형태로 쉽게 객체를 만듦
        // 리턴타입이 builder 이기때문에 계속해서 체이닝으로 연결 가능

        // /api/v1/posts?keword=스프링&author=yun&page=2&size=5
        // 조건문 분기를 여기서 함 (where절 조건 적는 것을 빌더를 통해서 구현 하는 것!)
        // 유틸리티 클래스 (변환 작업 등.. 가능한) 는 static으로 선언되어있어서 클래스로 접근 가능함
        
        // 검색어 포함 (제목이나 본문에 포함)
        if (StringUtils.hasText(requset.getKeyword())){
            builder.and(post.title.contains(requset.getKeyword())
                    .or(post.body.contains(requset.getKeyword()))

            );
        }

        // 작성자 일치 여부 where author = requset.getAuthor()
        if(StringUtils.hasText(requset.getAuthor())){
            builder.and(post.author.eq(requset.getAuthor()));
        }

        // 작성일 이후 - 타입이 string이 아니라서 다르게
        if(requset.getCreatedAt() != null){
            builder.and(post.createdAt.goe(requset.getCreatedAt()));
        }

        Pageable pageable = PageRequest.of(requset.getPage(), requset.getSize());


        List<Post> posts = queryFactory.selectFrom(post)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        // 전체 카운트를 계산하기 위해서 (프론트에서 페이징 처리 편하게 하기 위해서 필요한 코드)
        long total = queryFactory.select(post.count())
                .from(post)
                .where(builder)
                .fetchOne();

        List<PostResponse> content = posts.stream().map(PostResponse::from).toList();
        return new PageImpl<>(content, pageable, total);

    }
}

package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.domain.QPost;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.dto.post.PostSearchRequset;
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

        // PageImpl 은 직접 만든 리스트로 Page 겍체 만들때 사용
        // queryFactory.fetch()로 가져온 **리스트(List)**를 Page<T> 타입으로 감싸야 할 때 사용
        // 1. content: 응답 데이터
        // 2. pageable: 클라이언트가 요청한 페이지 정보 (page, size, 정렬 등)
        // 3. total: 전체 데이터 개수 (프론트에서 totalPages 계산 등에 사용)
        // PageImpl은 이 세 개를 받아서 Page<PostResponse> 객체로 포장해주는 거예요.

    }
    
    @Override
    public Page<PostResponse> search(PostSearchRequset requset) {
        // QueryDSL 로 동적 쿼리 만들기
        QPost post = QPost.post;


        //BooleanBuilder ( 흐름만 알기. 쿼리dsl 사용한다)
        BooleanBuilder builder = new BooleanBuilder(); // 빌더 패턴이 존재함 -> 체이닝 형태로 쉽게 객체를 만듦
        // 리턴타입이 builder 이기때문에 계속해서 체이닝으로 연결 가능
        // where 조건문만 처리 가능함. 정렬은 따로 처리해줘야함

        // /api/v1/posts?keword=스프링&author=yun&page=2&size=5
        // 조건문 분기를 여기서 함 (where절 조건 적는 것을 빌더를 통해서 구현 하는 것!)
        // 유틸리티 클래스 (변환 작업 등.. 가능한) 는 static으로 선언되어있어서 클래스로 접근 가능함
        
        // 검색어 포함 (제목이나 본문에 포함)
        //StringUtils 는 DSL 전용 메서드 아닌, 일반 자바 메서드
        // " " -> 원래 문자열이 있다고 판단하나, 이 메서드 사용 시 빈 문자열로 파악

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

        // limit & offset 은 페이징 처리를 위한 것
        // limit = 한번에 몇개 가져올지 제한하는 역할
        // offset 은 현재 페이지에 대한 목록을 가져오기 위한 것.
        // 만약 현재 2페이지 (page = 2), 목록이 10개 (size = 10)노출 된다고 했을 때
        // 21~30 까지 가져오기 위한 것  page * size = 20 = offset (0페이지부터 시작, 3번째 페이지임)
        // 이건 DB 조회시 제한하기 위한 역할 => 쿼리 실행 시, 필요한 정보만 가져오기 위함
        List<Post> posts = queryFactory.selectFrom(post)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        // 전체 카운트를 계산하기 위해서 (프론트에서 페이징 처리 편하게 하기 위해서 필요한 코드)
        long total = queryFactory.select(post.count())
                .from(post)
                .where(builder)
                .fetchOne(); // 결과값이 한개 일때 사용  = 단 건 조회일 (카운트..)
        // 여러 건 = 복수 데이터 조회 시, fetch() 사용

        List<PostResponse> content = posts.stream().map(PostResponse::from).toList();

        // 여기에서 페이지 정보를 넣어주는 것
        return new PageImpl<>(content, pageable, total);

    }
}

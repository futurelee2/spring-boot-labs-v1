package com.example.ch4labs.repository.Review;

import com.example.ch4labs.domain.QReview;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.Review.ReviewResponse;
import com.example.ch4labs.dto.Review.ReviewSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Page<ReviewResponse> search(ReviewSearchRequest request) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 책 제목 포함
        if (StringUtils.hasText(request.getBookTitleContains())) {
            builder.and(review.bookTitle.contains(request.getBookTitleContains()));
        }

        // 평점 범위
        if ((request.getMinRating() != null && request.getMaxRating() != null)) {
            builder.and(review.rating.between(request.getMinRating(), request.getMaxRating()));
        } else if (request.getMinRating() != null) {
            builder.and(review.rating.goe(request.getMinRating()));
        } else if (request.getMaxRating() != null) {
            builder.and(review.rating.loe(request.getMaxRating()));
        }


        // 리뷰 제목 키워드 포함
        if (StringUtils.hasText(request.getTitleContains())) {
            builder.and(review.title.contains(request.getTitleContains()));
        }


        // 책 저자 정확일치
        if(StringUtils.hasText(request.getBookAuthor())){
            builder.and(review.bookAuthor.eq(request.getBookAuthor()));
        }

        // 리뷰 본문 키워드
        if(StringUtils.hasText(request.getContentContains())){
            builder.and(review.content.contains(request.getContentContains()));
        }

        // 평점 정확히 일치
        if(request.getRating() != null){
            builder.and(review.rating.eq(request.getRating()));
        }

        // 책 제목 정확히 일치
        if(StringUtils.hasText(request.getBookTitle())){
            builder.and(review.bookTitle.eq(request.getBookTitle()));
        }

        // 작성자 author 정확히 일치
        if(StringUtils.hasText(request.getAuthor())){
            builder.and(review.author.eq(request.getAuthor()));
        }

        // QueryDSL 에서 정렬 기준을 설정할 때 사용하는 객체 => orderBt() 에 넘길수 있는 정렬기준 객체
        OrderSpecifier<?> orderSpecifier = review.createdAt.desc(); // 기본 설정 (정렬기중 + 정렬 방향 순으로 설정)
            // <?> : String author , Integer = rating..

        if(StringUtils.hasText(request.getSort())){
           String[] sortParts  = request.getSort().split(",");
           String sortField = sortParts[0];
           String direction = sortParts.length >1 ? sortParts[1]:"desc";

           switch (sortField) {
            // 최신순 정렬 (createdAt, desc)
            case "createdAt":
                    orderSpecifier = direction.equalsIgnoreCase("desc") ?
                            review.createdAt.desc() : review.createdAt.asc();
                    break;
            // 평점 오름차순 (rating , asc)
            case "rating":

                orderSpecifier = direction.equalsIgnoreCase("desc")?
                        review.rating.desc() : review.rating.asc();
                break;
            default:
                break;

           }
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Review> reviews = queryFactory.selectFrom(review)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        List<ReviewResponse> content = reviews.stream().map(ReviewResponse::from).toList();

        return new PageImpl<>(content, pageable, total);


    }

    }


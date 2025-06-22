package com.example.ch4labs.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//public class ReviewSearchRequest { // 기존 JPQL 용
//    private String bookTitle;
//    private String author;
//    private Integer rating;
//    private Integer minRating;
//    private Integer maxRating;
//    private int page = 0;
//}


public class ReviewSearchRequest {
    private String author; // 리뷰 작성자 정확일치
    private String bookTitle; // 책 제목 정확 일치
    private String bookTitleContains; // 책 제목 키워드 포함
    private String bookAuthor; // 책 저자 정확 일치
    private String titleContains; // 리뷰제목 키워드 포함
    private String contentContains; // 리뷰 본문 키워드 포함
    private Integer rating;
    private Integer minRating;
    private Integer maxRating;
    private String sort; // 정렬기준
    private int page = 0;
    private int size = 10;


}
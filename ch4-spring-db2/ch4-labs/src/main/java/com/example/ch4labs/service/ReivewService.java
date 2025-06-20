package com.example.ch4labs.service;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.*;
import com.example.ch4labs.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ReivewService {
    private final ReviewRepository repository;

    public ReviewResponse create(ReviewCreate request) {
        // request 라는 변수로 받아서 인스턴스화 되어 있기때문에, 내부 메서드 접근이 가능함.
        Review review = request.toDomian();
        Review saved = repository.save(review);
        // ReviewResponse 의 인스턴스가 만들어져있지 않아서, static 을 선언해서 접근 가능하도록.
        return ReviewResponse.from(saved);
    }
    @Transactional(readOnly = true)
    public List<ReviewResponse> getAll() {
        List<Review> review = repository.findAll();
        return review.stream().map(reviews -> ReviewResponse.from(reviews)).toList();
    }

    public ReviewResponse update(Long id, ReviewUpdate request) {
        Review review= repository.findById(id).orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setAuthor(request.getAuthor());
        review.setBookTitle(request.getBookTitle());
        review.setBookAuthor(request.getBookAuthor());
        review.setRating(request.getRating());
        return ReviewResponse.from(review);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

//    public ReviewPageResponse search(ReviewSearchRequest search) {
//        Pageable pageable = PageRequest.of(search.getPage(),search.getSize());
//        Page<Review> searchReview = repository.findByTitleContaining(search.getBookTitle(), pageable);
//        Page<ReviewResponse> pageResponse = (searchReview.map(review -> ReviewResponse.from(review)));
//
//        return ReviewPageResponse.from(pageResponse.getContent(),pageResponse.getTotalPages(), pageResponse.getTotalElements(), search);
//    }

    public ReviewPageResponse search(ReviewSearchRequest search) {
            Pageable pageable = PageRequest.of(search.getPage(),search.getSize());
            Page<Review> searchReview = repository.findByTitleContaining(search.getBookTitle(), pageable);

    }




}

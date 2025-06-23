package com.example.ch4labs.controller;

import com.example.ch4labs.dto.Review.*;
import com.example.ch4labs.service.ReivewService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReivewService service;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewCreate request){
        return ResponseEntity.ok(service.create(request));
    }
//
//    @GetMapping
//    public ResponseEntity<List<ReviewResponse>> getAll(){
//        return ResponseEntity.ok(service.getAll());
//    }

    @GetMapping
    public ResponseEntity<ReviewPageResponse> getSearch(ReviewSearchRequest request){
        return ResponseEntity.ok(service.search(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long id, @RequestBody ReviewUpdate update){
        return ResponseEntity.ok(service.update(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

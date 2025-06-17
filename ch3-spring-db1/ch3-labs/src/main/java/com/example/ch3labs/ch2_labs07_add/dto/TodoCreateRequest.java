package com.example.ch3labs.ch2_labs07_add.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class TodoCreateRequest {
    private String title;
    private Boolean completed;
}

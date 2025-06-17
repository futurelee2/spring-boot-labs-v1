package com.example.ch3labs.ch2_labs07_add.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private Boolean completed;


}

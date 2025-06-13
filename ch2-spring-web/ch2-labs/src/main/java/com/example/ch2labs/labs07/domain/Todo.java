package com.example.ch2labs.labs07.domain;

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

package com.example.ch2labs.labs09;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TodoDto {
    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;
}

package com.example.ch3labs.ch2_labs07_add.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TodoUpdateRequest {
    private String title;
    private Boolean completed;

}

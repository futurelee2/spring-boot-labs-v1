package com.example.ch2labs.labs08.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Reserve {
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

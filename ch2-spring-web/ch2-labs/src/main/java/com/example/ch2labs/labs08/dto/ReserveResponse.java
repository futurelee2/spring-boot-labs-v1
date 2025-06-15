package com.example.ch2labs.labs08.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReserveResponse {
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

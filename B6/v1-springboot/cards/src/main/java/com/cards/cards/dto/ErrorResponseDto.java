package com.cards.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private String errorStatus;
    private String errorMessage;
    private LocalDateTime dateTime;
}

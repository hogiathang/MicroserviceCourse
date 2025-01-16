package com.loans.loans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "Error response object")
public class ErrorResponseDto {
    @Schema(description = "API path", example = "/api/create")
    private String apiPath;

    @Schema(description = "HTTP status code", example = "417")
    private HttpStatus errorCode;

    @Schema(description = "Error message", example = "Invalid input")
    private String errorMessage;

    @Schema(description = "Error time", example = "2021-09-01T12:00:00")
    private LocalDateTime errorTime;
}

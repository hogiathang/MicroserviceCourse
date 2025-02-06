package com.cards.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponseDto", description = "Data Transfer Object for Error Response")
public class ErrorResponseDto {
    @Schema(
            description = "API Path",
            example = "/cards/v1/cards"
    )
    private String apiPath;

    @Schema(
            description = "Error Status",
            example = "417"
    )
    private String errorStatus;

    @Schema(
            description = "Error Message",
            example = "Invalid Input"
    )
    private String errorMessage;

    @Schema(
            description = "Error Description",
            example = "Invalid Input"
    )
    private LocalDateTime dateTime;
}

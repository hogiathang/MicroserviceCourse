package com.accounts.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema (
        name= "ErrorResponseDto",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema (
            description = "API path"
    )
    private String apiPath;
    @Schema (
            description = "Error code"
    )
    private HttpStatus errorCode;

    @Schema (
            description = "Error message"
    )
    private String errorMessage;

    @Schema (
            description = "Error time", example = "2021-09-01T12:00:00Z"
    )
    private String errorTime;
}

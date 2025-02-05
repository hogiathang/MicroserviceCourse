package com.cards.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ResponseDto", description = "Response object")
public class ResponseDto {
    @Schema(description = "Status code")
    private String statusCode;

    @Schema(description = "Message")
    private String message;
}

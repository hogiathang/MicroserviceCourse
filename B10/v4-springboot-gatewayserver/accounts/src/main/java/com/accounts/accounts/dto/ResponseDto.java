package com.accounts.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema (
        name= "ResponseDto",
        description = "Schema to hold response information"
)
public class ResponseDto {
    @Schema (
            description = "Status code", example = "200"
    )
    private String statusCode;

    @Schema (
            description = "Status message", example = "Success"
    )
    private String statusMsg;
}

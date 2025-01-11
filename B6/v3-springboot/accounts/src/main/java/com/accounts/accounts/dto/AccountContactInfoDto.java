package com.accounts.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Schema(name = "AccountContactInfoDto", description = "Account contact information")
@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class AccountContactInfoDto {
    @Schema(description = "Message to be displayed", example = "Please contact us for any queries")
    private String message;

    @Schema(description = "Contact details", example = "{ \"email\": yywdijdiwdi@gmail.com, \"name\": \"John\" }")
    private Map<String, String> contactDetails;

    @Schema(description = "On call support", example = "[\"038277251\"]")
    private List<String> onCallSupport;
}

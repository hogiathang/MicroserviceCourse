package com.cards.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
@Getter @Setter
@Schema(name = "CardsContactDto", description = "Contact object")
public class CardsContactDto{
    @Schema(description = "Message")
    private String message;

    @Schema(description = "Contact details")
    private Map<String, String> contactDetails;

    @Schema(description = "On call support")
    private List<String> onCallSupport;
}

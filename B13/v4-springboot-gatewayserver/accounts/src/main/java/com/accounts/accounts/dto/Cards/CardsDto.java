package com.accounts.accounts.dto.Cards;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CardsDto", description = "Data Transfer Object for Cards")
public class CardsDto {

    @Schema(description = "Card Id", example = "1")
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Card Number", example = "123456789012")
    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    private String cardNumber;

    @Schema(description = "Card Type", example = "Credit")
    @NotEmpty(message = "CardType can not be a null or empty")
    private String cardType;

    @Schema(description = "Total card limit", example = "10000")
    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @Schema(description = "Total amount used", example = "5000")
    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private int amountUsed;

    @Schema(description = "Total available amount", example = "5000")
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private int availableAmount;

}

package com.accounts.accounts.dto.Detail;

import com.accounts.accounts.dto.Cards.CardsDto;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.dto.Loans.LoansDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Schema(
        name = "CustomerDetailDto",
        description = "This schema using to hold the customer account, card and loan details"
)
public class CustomerDetailDto extends CustomerDto {
    @Schema(
            description = "Loans details of the customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Cards details of the customer"
    )
    private CardsDto cardsDto;
}

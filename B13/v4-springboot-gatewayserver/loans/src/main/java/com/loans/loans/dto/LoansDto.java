package com.loans.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Loans data transfer object")
public class LoansDto {
    @Schema(description = "Mobile number", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
    @NotEmpty(message = "Mobile number cannot be empty or null")
    private String mobileNumber;

    @Schema(description = "Loan number", example = "LN123456")
    @NotEmpty(message = "Loan number cannot be empty or null")
    private String loanNumber;

    @Schema(description = "Loan type", example = "Home loan")
    @NotEmpty(message = "Loan type cannot be empty or null")
    private String loanType;

    @Schema(description = "Total loan amount", example = "100000")
    @Positive(message = "Total loan amount must be greater than 0")
    private int totalLoan;

    @Schema(description = "Amount paid", example = "50000")
    @PositiveOrZero(message = "Amount paid must be greater than or equal to 0")
    private int amountPaid;

    @Schema(description = "Outstanding amount", example = "50000")
    @PositiveOrZero(message = "Outstanding amount must be greater than or equal to 0")
    private int outstandingAmount;
}

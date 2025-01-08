package com.loans.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
    @NotEmpty(message = "Mobile number cannot be empty or null")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be empty or null")
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty or null")
    private String loanType;

    @Positive(message = "Total loan amount must be greater than 0")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid must be greater than or equal to 0")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount must be greater than or equal to 0")
    private int outstandingAmount;
}

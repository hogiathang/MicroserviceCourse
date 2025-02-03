package com.accounts.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema (
        name= "CustomerDto",
        description = "Schema to hold customer information"
)
public class CustomerDto {

    @Schema (
            description = "Name of the customer", example = "Ho Gia Thang"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 50, message = "Name should have at least 5 characters")
    private String name;

    @Schema (
            description = "Email of the customer", example = "hogiathang2@gmail.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid value")
    private String email;

    @Schema (
            description = "Mobile number of the customer", example = "0123456789"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
    private String mobileNumber;

    @Schema (
            description = "Address of the customer"
    )
    private AccountsDto accountsDto;
}

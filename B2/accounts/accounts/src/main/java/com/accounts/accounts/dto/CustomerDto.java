package com.accounts.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min= 5, max = 50, message = "Name should have at least 5 characters")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}

package com.accounts.accounts.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class AccountsDto {
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

}

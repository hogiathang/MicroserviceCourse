package com.accounts.accounts.entity;

import jakarta.persistence.*;

@Entity
public class Accounts extends BaseEntity{
    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}

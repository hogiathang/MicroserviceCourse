package com.accounts.accounts.service;

import com.accounts.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * Create account
     * @param customerDto - customerDto object
     */
    void createAccount(CustomerDto customerDto);
}

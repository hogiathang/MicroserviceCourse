package com.accounts.accounts.service;

import com.accounts.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * Create account
     *
     * @param customerDto - customerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetch account details
     *
     * @param mobileNumber - mobile number
     */
    CustomerDto fetchAccountDetails(String mobileNumber);

    /**
     * Update account
     *
     * @param customerDto - customerDto object
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Delete account
     *
     * @param mobileNumber - mobile number
     */
    boolean deleteAccount(String mobileNumber);
}

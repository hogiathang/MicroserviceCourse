package com.loans.loans.service;

import com.loans.loans.dto.LoansDto;

public interface ILoansService {
    /**
     * This method is used to create a loan
     *
     * @param mobileNumber
     */
    void createLoan(String mobileNumber);

    /**
     * This method is used to fetch loan details
     *
     * @param mobileNumber
     */
    LoansDto fetchLoans(String mobileNumber);

    /**
     * This method is used to update loan details
     *
     * @param loansDto
     */
    boolean updateLoans(LoansDto loansDto);

    /**
     * This method is used to delete loan details
     *
     * @param mobileNumber
     */
    boolean deleteLoans(String mobileNumber);
}

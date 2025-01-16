package com.accounts.accounts.service.impl;

import com.accounts.accounts.dto.Cards.CardsDto;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.dto.Detail.CustomerDetailDto;
import com.accounts.accounts.dto.Loans.LoansDto;
import com.accounts.accounts.mapper.CustomerMapper;
import com.accounts.accounts.service.IAccountService;
import com.accounts.accounts.service.ICustomerService;
import com.accounts.accounts.service.client.CardsFeignClient;
import com.accounts.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private CardsFeignClient cardsFeignClient;
    private IAccountService accountService;
    private LoansFeignClient loansFeignClient;

    /**
     * Fetch customer details
     * @param mobileNumber - mobile number
     * @return CustomerDetailDto
     */
    @Override
    public CustomerDetailDto fetchCustomerDetails(String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        CardsDto cardsDto = cardsFeignClient.fetchCard(mobileNumber).getBody();
        LoansDto loansDto = loansFeignClient.fetchLoanDetails(mobileNumber).getBody();

        CustomerDetailDto customerDetailDto = CustomerMapper.mapToCustomerDetailDto(customerDto, new CustomerDetailDto());
        customerDetailDto.setLoansDto(loansDto);
        customerDetailDto.setCardsDto(cardsDto);

        return customerDetailDto;
    }
}

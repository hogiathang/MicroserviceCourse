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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    @Qualifier("com.accounts.accounts.service.client.CardsFeignClient")
    private CardsFeignClient cardsFeignClient;
    private IAccountService accountService;
    @Qualifier("com.accounts.accounts.service.client.LoansFeignClient")
    private LoansFeignClient loansFeignClient;

    /**
     * Fetch customer details
     * @param mobileNumber - mobile number
     * @return CustomerDetailDto
     */
    @Override
    public CustomerDetailDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);

        ResponseEntity<CardsDto> responseCardsDto = cardsFeignClient.fetchCard(correlationId, mobileNumber);

        ResponseEntity<LoansDto> responseLoansDto = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);

        CustomerDetailDto customerDetailDto = CustomerMapper.mapToCustomerDetailDto(customerDto, new CustomerDetailDto());

        if (responseCardsDto != null) {
            customerDetailDto.setCardsDto(responseCardsDto.getBody());
        }
        if (responseLoansDto != null) {
            customerDetailDto.setLoansDto(responseLoansDto.getBody());
        }

        return customerDetailDto;
    }
}

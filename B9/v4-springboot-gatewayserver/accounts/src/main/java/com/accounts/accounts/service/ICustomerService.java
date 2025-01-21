package com.accounts.accounts.service;

import com.accounts.accounts.dto.Detail.CustomerDetailDto;

public interface ICustomerService {

    CustomerDetailDto fetchCustomerDetails(String mobileNumber, String correlationId);
}

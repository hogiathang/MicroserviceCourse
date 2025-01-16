package com.accounts.accounts.mapper;

import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.dto.Detail.CustomerDetailDto;
import com.accounts.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDetailDto mapToCustomerDetailDto(CustomerDto customerDto, CustomerDetailDto customerDetailDto) {
        customerDetailDto.setName(customerDto.getName());
        customerDetailDto.setEmail(customerDto.getEmail());
        customerDetailDto.setMobileNumber(customerDto.getMobileNumber());
        customerDetailDto.setAccountsDto(customerDto.getAccountsDto());
        return customerDetailDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}

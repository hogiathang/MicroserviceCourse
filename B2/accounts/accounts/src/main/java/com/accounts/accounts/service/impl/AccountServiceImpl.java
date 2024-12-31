package com.accounts.accounts.service.impl;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.entity.Accounts;
import com.accounts.accounts.entity.Customer;
import com.accounts.accounts.exception.CustomerAlreadyExistsException;
import com.accounts.accounts.mapper.CustomerMapper;
import com.accounts.accounts.repository.AccountsRepository;
import com.accounts.accounts.repository.CustomerRepository;
import com.accounts.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Create account
     * @param customerDto - customerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> customerOptional =
                customerRepository.findByMobileNumber(customer.getMobileNumber());
        customerOptional.ifPresent(c -> {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with this mobile number" + c.getMobileNumber()
            );
        });

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;
    }
}

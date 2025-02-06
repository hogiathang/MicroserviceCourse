package com.accounts.accounts.service.impl;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.AccountMessageDto;
import com.accounts.accounts.dto.AccountsDto;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.entity.Accounts;
import com.accounts.accounts.entity.Customer;
import com.accounts.accounts.exception.CustomerAlreadyExistsException;
import com.accounts.accounts.exception.ResourceNotFoundException;
import com.accounts.accounts.mapper.AccountsMapper;
import com.accounts.accounts.mapper.CustomerMapper;
import com.accounts.accounts.repository.AccountsRepository;
import com.accounts.accounts.repository.CustomerRepository;
import com.accounts.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    /**
     * Create account
     *
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
        Accounts savedAccount  = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    private void sendCommunication(Accounts accounts, Customer customer) {
        var accountMessageDto = new AccountMessageDto(
                accounts.getAccountNumber(),
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
        logger.info("Sending message to the customer: {}", accountMessageDto);
        var result = streamBridge.send("sendCommunication-out-0", accountMessageDto);
        logger.info("Is communication request successfully processed?: {}", result);
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

    /**
     * Fetch account details
     *
     * @param mobileNumber - mobile number
     */
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    /**
     * Update account
     *
     * @param customerDto - customerDto object
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(
                    accountsDto.getAccountNumber()
            ).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);

            customerRepository.save(customer);

            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Delete account
     *
     * @param mobileNumber - mobile number
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());

        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

    /**
     * Update communication status
     * @param accountNumber
     * @return
     */
    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if(accountNumber !=null ){
            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
            );
            accounts.setCommunicationSw(true);
            accountsRepository.save(accounts);
            isUpdated = true;
        }
        return  isUpdated;
    }
}

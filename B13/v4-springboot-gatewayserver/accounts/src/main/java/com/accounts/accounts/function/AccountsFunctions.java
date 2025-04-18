package com.accounts.accounts.function;

import com.accounts.accounts.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountService accountService) {
        return accountNumber -> {
            log.info("Updating communication for account number {}", accountNumber.toString());
            accountService.updateCommunicationStatus(accountNumber);
        };
    }
}

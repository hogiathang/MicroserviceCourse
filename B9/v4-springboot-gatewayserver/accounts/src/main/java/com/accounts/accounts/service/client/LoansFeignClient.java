package com.accounts.accounts.service.client;

import com.accounts.accounts.dto.Loans.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans")
public interface LoansFeignClient {
    @GetMapping(value = "/api/fetch", produces = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestHeader(value = "hogiathang-correlation-id") String correlationId,
            @RequestParam String mobileNumber
    );
}

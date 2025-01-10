package com.loans.loans.controller;

import com.loans.loans.constants.LoansConstants;
import com.loans.loans.dto.LoansDto;
import com.loans.loans.dto.LoansContactInfoDto;
import com.loans.loans.dto.ResponseDto;
import com.loans.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api", produces = "application/json")
@RestController
@Validated
public class LoansController {
    @Autowired
    private ILoansService loansService;

    @Value("${build.version}")
    private String version;

    @Autowired
    private LoansContactInfoDto loansInfoDto;

    @Autowired
    private Environment environment;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(201)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {
        return ResponseEntity
                .status(200)
                .body(loansService.fetchLoans(mobileNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(
            @RequestBody @Valid
            LoansDto loansDto) {
        boolean isUpdated = loansService.updateLoans(loansDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(200)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(417)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {
        boolean isDeleted = loansService.deleteLoans(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(200)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(417)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(version);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansInfoDto);
    }
}

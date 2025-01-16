package com.accounts.accounts.controller;

import com.accounts.accounts.dto.Detail.CustomerDetailDto;
import com.accounts.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name= "Customer in my application",
        description = "This API allows to show information about the customer"
)
@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Validated
@AllArgsConstructor
public class CustomerController {

    private ICustomerService iCustomerService;

    @Operation(
            summary = "Fetch customer details REST API",
            description = "This API allows to fetch account details of a customer by mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailDto> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {
        CustomerDetailDto customerDetailDto = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailDto);
    }
}

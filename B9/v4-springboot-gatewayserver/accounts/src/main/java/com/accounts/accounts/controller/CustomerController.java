package com.accounts.accounts.controller;

import com.accounts.accounts.dto.Detail.CustomerDetailDto;
import com.accounts.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name= "Customer in my application",
        description = "This API allows to show information about the customer"
)
@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Validated
@AllArgsConstructor
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
            @RequestHeader(value = "hogiathang-correlation-id") String correlationId,
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {

        logger.debug("hogiathang-correlation-id found : {}", correlationId);
        CustomerDetailDto customerDetailDto = iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailDto);
    }
}

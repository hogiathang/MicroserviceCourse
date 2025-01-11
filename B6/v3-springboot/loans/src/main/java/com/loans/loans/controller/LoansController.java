package com.loans.loans.controller;

import com.loans.loans.constants.LoansConstants;
import com.loans.loans.dto.ErrorResponseDto;
import com.loans.loans.dto.LoansDto;
import com.loans.loans.dto.LoansInfoDto;
import com.loans.loans.dto.ResponseDto;
import com.loans.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD Loans API",
        description = "Perform CRUD operations on loans"
)
public class LoansController {
    @Autowired
    private ILoansService loansService;

    @Operation(
            summary = "Create a new loan",
            description = "Create a new loan for a customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Loan created successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(201)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch loan details",
            description = "Fetch loan details for a customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Loan details fetched successfully"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
            String mobileNumber) {
        return ResponseEntity
                .status(200)
                .body(loansService.fetchLoans(mobileNumber));
    }

    @Operation(
            summary = "Update loan details",
            description = "Update loan details for a customer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details updated successfully"),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed to update loan details",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
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

    @Operation(
            summary = "Delete loan details",
            description = "Delete loan details for a customer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details deleted successfully"),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed to delete loan details",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
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


}

package com.cards.cards.controller;

import com.cards.cards.constants.CardsConstants;
import com.cards.cards.dto.CardsContactDto;
import com.cards.cards.dto.CardsDto;
import com.cards.cards.dto.ErrorResponseDto;
import com.cards.cards.dto.ResponseDto;
import com.cards.cards.service.ICardsService;
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

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Validated
@Tag(
        name = "CRUD REST API for Cards microservice controller",
        description = "This API allows to perform CRUD operations on cards"
)
public class CardController {
    @Autowired
    private ICardsService cardsService;

    @Operation(
            summary = "Create card REST API",
            description = "This API allows to create a new card in the bank"
    )
    @ApiResponse (
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch card REST API",
            description = "This API allows to fetch card details of a customer by mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsService.fetchCard(mobileNumber));
    }

    @Operation(
            summary = "Update card REST API",
            description = "This API allows to update card details of a customer by mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION_FAILED",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(
            @Valid @RequestBody
            CardsDto cardsDto
    ) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if (!isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
    }

    @Operation(
            summary = "Delete card REST API",
            description = "This API allows to delete card details of a customer by mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION_FAILED",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);
        if (!isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
    }
}

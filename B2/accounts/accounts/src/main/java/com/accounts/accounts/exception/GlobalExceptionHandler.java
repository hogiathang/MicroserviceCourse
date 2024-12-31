package com.accounts.accounts.exception;

import com.accounts.accounts.dto.ErrorResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(CustomerAlreadyExistsException.class)
//    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex,
//                                                                                 WebRequest request) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
//                request.getDescription(false),
//                HttpStatus.BAD_REQUEST,
//                ex.getMessage(),
//                LocalDateTime.now().toString()
//        );
//
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
//                                                                                 WebRequest request) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
//                request.getDescription(false),
//                HttpStatus.NOT_FOUND,
//                ex.getMessage(),
//                LocalDateTime.now().toString()
//        );
//
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex,
                                                            WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

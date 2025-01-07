package com.loans.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {
    public ResouceNotFoundException(String resouceName, String fieldName, String fieldValue) {
        super(
                String.format("%s not found with %s : '%s'", resouceName, fieldName, fieldValue)
        );
    }
}

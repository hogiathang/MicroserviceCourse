package com.accounts.accounts.service.client;

import com.accounts.accounts.dto.Cards.CardsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> fetchCard(String correlationId, String mobileNumber) {
        return null;
    }
}

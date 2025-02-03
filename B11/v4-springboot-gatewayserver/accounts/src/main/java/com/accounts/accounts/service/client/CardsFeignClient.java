package com.accounts.accounts.service.client;

import com.accounts.accounts.dto.Cards.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFallBack.class)
public interface CardsFeignClient {
    @GetMapping(value="/api/fetch", produces = "application/json")
    public ResponseEntity<CardsDto> fetchCard(
            @RequestHeader(value = "hogiathang-correlation-id") String correlationId,
            @RequestParam String mobileNumber
    );
}

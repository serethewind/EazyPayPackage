package com.eazypay.accounts.service.client;

import com.eazypay.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber, String correlationId) {
        return null;
    }
}

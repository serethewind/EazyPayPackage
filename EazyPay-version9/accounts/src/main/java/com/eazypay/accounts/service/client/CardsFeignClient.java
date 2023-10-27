package com.eazypay.accounts.service.client;

import com.eazypay.accounts.dto.CardsDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", path = "api")
public interface CardsFeignClient {

    @GetMapping("fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber, @RequestHeader("eazypay-correlation-id") String correlationId);
}

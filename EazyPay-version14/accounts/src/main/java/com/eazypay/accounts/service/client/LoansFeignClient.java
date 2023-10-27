package com.eazypay.accounts.service.client;

import com.eazypay.accounts.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(path = "api", name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping("fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber, @RequestHeader("eazypay-correlation-id") String correlationId);
}

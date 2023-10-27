package com.eazypay.accounts.controller;

import com.eazypay.accounts.dto.CustomerDetailsDto;
import com.eazypay.accounts.dto.ErrorResponseDto;
import com.eazypay.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "Customer Details Rest API",
        description = "Rest APIs in EazyPay to fetch customer, account, loans and cards information"
)
@AllArgsConstructor
public class CustomerController {
    private ICustomerService iCustomerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Operation(
            summary = "Fetch customer details Rest API",
            description = "Rest API to fetch aggregate customer information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION_FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber, @RequestHeader("eazypay-correlation-id") String correlationId) {
        logger.debug("eazypay-correlation-id found: {} ", correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}

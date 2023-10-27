package com.eazypay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(description = "API path invoked by client")
    private String apiPath;
    @Schema(description = "Error code for the error instance")
    private HttpStatus errorCode;
    @Schema(description = "Error message for the error instance")
    private String errorMessage;
    @Schema(description = "Time when error instance happened")
    private LocalDateTime errorTime;
}

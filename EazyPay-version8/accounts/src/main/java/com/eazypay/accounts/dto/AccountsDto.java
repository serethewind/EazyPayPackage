package com.eazypay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account number of the customer", example = "1234567890"
    )
    private Long accountNumber;
    @NotEmpty(message = "AccountType cannot be a null or empty")
    @Schema(description = "Account type. Could be Savings, Current", example = "Savings")
    private String accountType;
    @NotEmpty(message = "AccountType cannot be a null or empty")
    @Schema(description = "Branch Address of EazyPay. Defaults to HQ", example = "7 Adekoya Street, Mende, Maryland")
    private String branchAddress;
}

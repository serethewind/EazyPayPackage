package com.eazypay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Customer Information",
        description = "Schema to hold customer information"
)
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema(
            description = "Full name of the customer", example = "Noah Johnson"
    )
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email address should be a valid value")
    @Schema(description = "Email of the customer", example = "noahjohnson@gmail.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(description = "10 digit mobile number of the customer", example = "1234567890")
    private String mobileNumber;
    @Schema(description = "Account details of the customer")
    private AccountsDto accountsDto;
}

package com.eazypay.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "Customer Details",
        description = "Schema to hold Customers, Accounts, Loans and Card Information"
)
public class CustomerDetailsDto {

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
    @Schema(description = "Loan details of the customer")
    private LoansDto loansDto;
    @Schema(description = "Card details of the customer")
    private CardsDto cardsDto;
}

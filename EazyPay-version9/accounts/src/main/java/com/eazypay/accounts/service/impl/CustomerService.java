package com.eazypay.accounts.service.impl;

import com.eazypay.accounts.dto.AccountsDto;
import com.eazypay.accounts.dto.CustomerDetailsDto;
import com.eazypay.accounts.entities.Accounts;
import com.eazypay.accounts.entities.Customer;
import com.eazypay.accounts.exception.ResourceNotFoundException;
import com.eazypay.accounts.mapper.AccountsMapper;
import com.eazypay.accounts.repository.AccountsRepository;
import com.eazypay.accounts.repository.CustomerRepository;
import com.eazypay.accounts.service.client.CardsFeignClient;
import com.eazypay.accounts.service.client.LoansFeignClient;
import com.eazypay.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private LoansFeignClient loansFeignClient;
    private CardsFeignClient cardsFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

      Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

      Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

      return CustomerDetailsDto.builder()
              .name(customer.getName())
              .email(customer.getEmail())
              .mobileNumber(customer.getMobileNumber())
              .accountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()))
              .loansDto(loansFeignClient.fetchLoanDetails(mobileNumber, correlationId).getBody())
              .cardsDto(cardsFeignClient.fetchCardDetails(mobileNumber, correlationId).getBody())
              .build();
    }
}

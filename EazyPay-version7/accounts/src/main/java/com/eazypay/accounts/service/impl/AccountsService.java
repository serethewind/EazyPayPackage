package com.eazypay.accounts.service.impl;

import com.eazypay.accounts.constants.AccountsConstants;
import com.eazypay.accounts.dto.AccountsDto;
import com.eazypay.accounts.dto.CustomerDto;
import com.eazypay.accounts.entities.Accounts;
import com.eazypay.accounts.entities.Customer;
import com.eazypay.accounts.exception.CustomerAlreadyExistsException;
import com.eazypay.accounts.exception.ResourceNotFoundException;
import com.eazypay.accounts.mapper.AccountsMapper;
import com.eazypay.accounts.mapper.CustomerMapper;
import com.eazypay.accounts.repository.AccountsRepository;
import com.eazypay.accounts.repository.CustomerRepository;
import com.eazypay.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.existsByMobileNumber(customerDto.getMobileNumber())) {
            throw new CustomerAlreadyExistsException(String.format("Customer already registered with given mobile number: '%s'", customerDto.getMobileNumber()));
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param mobileNumber -Input mobile number
     * @return Customer based on the mobile number. It handles exception in case the mobile number is not present.
     */
    @Override
    public CustomerDto fetchAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    /**
     * @param customerDto - Input customer dto
     * @return
     */

//    @Override
//    public boolean updateAccount(CustomerDto customerDto) {
//        boolean isUpdated = false;
//        AccountsDto accountsDto = customerDto.getAccountsDto();
//        if (accountsDto != null) {
//            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));
//
//            Accounts savedAccount = AccountsMapper.mapToAccounts(accountsDto, accounts);
//            accountsRepository.save(savedAccount);
//
//            Long customerId = savedAccount.getCustomerId();
//            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));
//
//            CustomerMapper.mapToCustomer(customerDto, customer);
//            customerRepository.save(customer);
//            isUpdated = true;
//
//        }
//        return isUpdated;
//    }
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );

            accounts.setAccountNumber(accounts.getAccountNumber());
            accounts.setAccountType(accountsDto.getAccountType());
            accounts.setBranchAddress(accountsDto.getBranchAddress());

            customer.setCustomerId(customerId);
            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setMobileNumber(customerDto.getMobileNumber());

            accountsRepository.save(accounts);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        customerRepository.deleteById(customer.getCustomerId());
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        return true;
    }

    /**
     * @param customer - Customer Entity
     * @return Accounts entity tied with the customer via the customerId in the paramater
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = Accounts.builder()
                .customerId(customer.getCustomerId())
                .accountNumber(generateAccount())
                .accountType(AccountsConstants.SAVINGS)
                .branchAddress(AccountsConstants.ADDRESS)
                .build();
        return newAccount;
    }

    /**
     * @return generated account
     */
    private Long generateAccount() {
        return 1000000000L + new Random().nextInt(900000000);
    }
}

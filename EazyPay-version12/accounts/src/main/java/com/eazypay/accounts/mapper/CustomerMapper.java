package com.eazypay.accounts.mapper;

import com.eazypay.accounts.dto.CustomerDto;
import com.eazypay.accounts.entities.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
       customerDto = CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
       return customerDto;
    }
}

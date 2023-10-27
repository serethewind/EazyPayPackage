package com.eazypay.accounts.service;

import com.eazypay.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber Takes mobileNumber as input
     * @return CustomerDetailsDto which is an aggregate of customer, account, loans and cards information
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}


package com.eazypay.accounts.service;

import com.eazypay.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto -Input customer dto
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber -Input mobile number
     * @return Customer based on the mobile number query
     */
    CustomerDto fetchAccountByMobileNumber(String mobileNumber);

    /**
     *
     * @param customerDto - Input customer dto
     * @return Boolean indicating if the update was successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Boolean indicating if the delete operation was successful or not
     */
    boolean deleteAccount(String mobileNumber);
}

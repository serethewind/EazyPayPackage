package com.eazypay.accounts.repository;

import com.eazypay.accounts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);

    Boolean existsByMobileNumber(String mobileNumber);


}

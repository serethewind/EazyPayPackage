package com.eazypay.accounts.repository;

import com.eazypay.accounts.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Modifying //tells spring jpa that this method is modifying data in our database
    @Transactional //should there be errors, there should be rollback to changes (partial changes) already caused by calling this method

    void deleteByCustomerId(Long customerId);

//    Optional<Accounts> findByAccountNumber(Long accountNumber);
}

package com.eazypay.accounts.mapper;

import com.eazypay.accounts.dto.AccountsDto;
import com.eazypay.accounts.entities.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){
       accountsDto = AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .build();
       return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){
        accounts = Accounts.builder()
                .accountNumber(accountsDto.getAccountNumber())
                .accountType(accountsDto.getAccountType())
                .branchAddress(accountsDto.getBranchAddress())
                .build();
        return accounts;
    }
 }

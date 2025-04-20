package com.ecom.cbdg.interview.application.service;


import com.ecom.cbdg.interview.application.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();

    Optional<Account> getAccountById(Long id);

    Account saveAccount(Account account);

    void deleteAccount(Long id);

    // Add the new methods for querying accounts
    List<Account> getAccountsByAccountType(String accountType);

    Account getAccountByBankAccountNumber(String bankAccountNumber);
}
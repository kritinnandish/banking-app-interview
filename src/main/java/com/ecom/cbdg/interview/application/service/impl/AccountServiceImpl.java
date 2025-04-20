package com.ecom.cbdg.interview.application.service.impl;

import com.ecom.cbdg.interview.application.model.Account;
import com.ecom.cbdg.interview.application.repository.AccountRepository;
import com.ecom.cbdg.interview.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    // Implement the new methods to use repository methods
    @Override
    public List<Account> getAccountsByAccountType(String accountType) {
        return accountRepository.findByAccountType(accountType);
    }

    @Override
    public Account getAccountByBankAccountNumber(String bankAccountNumber) {
        return accountRepository.findByBankAccountNumber(bankAccountNumber);
    }
}
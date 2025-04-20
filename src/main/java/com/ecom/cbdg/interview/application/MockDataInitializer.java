package com.ecom.cbdg.interview.application;

import com.ecom.cbdg.interview.application.model.Account;
import com.ecom.cbdg.interview.application.model.Transaction;
import com.ecom.cbdg.interview.application.repository.AccountRepository;
import com.ecom.cbdg.interview.application.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class MockDataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public MockDataInitializer(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create sample accounts
        Account account1 = Account.builder()
                .bankAccountNumber("12345")
                .accountType("Savings")
                .balance(1000.00)
                .build();

        Account account2 = Account.builder()
                .bankAccountNumber("67890")
                .accountType("Checking")
                .balance(500.00)
                .build();

        // Save accounts
        account1 = accountRepository.save(account1);
        account2 = accountRepository.save(account2);

        // Create sample transactions for account1
        Transaction transaction1 = Transaction.builder()
                .transactionType("credit")
                .amount(200.00)
                .description("Deposit")
                .date(LocalDate.of(2025, 4, 10))
                .account(account1)
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionType("debit")
                .amount(50.00)
                .description("Withdrawal")
                .date(LocalDate.of(2025, 4, 15))
                .account(account1)
                .build();

        // Create sample transactions for account2
        Transaction transaction3 = Transaction.builder()
                .transactionType("debit")
                .amount(100.00)
                .description("Transfer")
                .date(LocalDate.of(2025, 4, 5))
                .account(account2)
                .build();

        Transaction transaction4 = Transaction.builder()
                .transactionType("credit")
                .amount(150.00)
                .description("Deposit")
                .date(LocalDate.of(2025, 4, 20))
                .account(account2)
                .build();

        // Save transactions
        transactionRepository.saveAll(Arrays.asList(transaction1, transaction2, transaction3, transaction4));
    }
}
package com.ecom.cbdg.interview.application.service.impl;


import com.ecom.cbdg.interview.application.model.Transaction;
import com.ecom.cbdg.interview.application.repository.TransactionRepository;
import com.ecom.cbdg.interview.application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getTransactionsByAccountAndMonth(Long accountId, int year, Month month) {
        // Calculate the start and end dates for the given month
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);  // Last day of the month

        // Call the repository method to find transactions by accountId and date range
        return transactionRepository.findByAccountIdAndDateBetween(accountId, startDate, endDate);
    }
}

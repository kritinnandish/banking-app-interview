package com.ecom.cbdg.interview.application.service;
import com.ecom.cbdg.interview.application.model.Transaction;

import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Optional<Transaction> getTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransaction(Long id);
    List<Transaction> getTransactionsByAccount(Long accountId);
    List<Transaction> getTransactionsByAccountAndMonth(Long accountId, int year, Month month);

}

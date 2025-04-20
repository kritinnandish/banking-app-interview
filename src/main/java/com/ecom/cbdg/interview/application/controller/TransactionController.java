package com.ecom.cbdg.interview.application.controller;

import com.ecom.cbdg.interview.application.model.Transaction;
import com.ecom.cbdg.interview.application.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction", description = "Operations related to transactions in the bank")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint to get all transactions (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @GetMapping
    @Operation(summary = "Get all transactions", description = "Fetches all transactions for the authenticated user.")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Endpoint to get transaction by ID (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Fetches a transaction by its ID.")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to create a new transaction (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Creates a new bank transaction.")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    // Endpoint to delete a transaction by ID (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a transaction by ID", description = "Deletes a transaction by its ID.")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get monthly statement (with year and Month enum as params)
    @PreAuthorize("hasRole('new_app_role')")
    @GetMapping("/statement/{accountId}/{year}/{month}")
    @Operation(summary = "Get monthly statement", description = "Fetches all transactions for the specified year, month, and account ID.")
    public ResponseEntity<List<Transaction>> getMonthlyStatement(
            @PathVariable Long accountId,
            @PathVariable int year,
            @PathVariable Month month) {

        List<Transaction> transactions = transactionService.getTransactionsByAccountAndMonth(accountId, year, month);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
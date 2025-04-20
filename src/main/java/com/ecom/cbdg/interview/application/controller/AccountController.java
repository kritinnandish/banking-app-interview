package com.ecom.cbdg.interview.application.controller;
import com.ecom.cbdg.interview.application.model.Account;
import com.ecom.cbdg.interview.application.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "Operations related to bank accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Endpoint to get all accounts (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @GetMapping
    @Operation(summary = "Get all accounts", description = "Fetches all bank accounts for the authenticated user.")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Endpoint to get account by ID (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Fetches a bank account by its ID.")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to create or update an account (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @PostMapping
    @Operation(summary = "Create or update an account", description = "Creates or updates a bank account.")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.saveAccount(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    // Endpoint to delete an account by ID (requires "new_app_role" role)
    @PreAuthorize("hasRole('new_app_role')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an account by ID", description = "Deletes a bank account by its ID.")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
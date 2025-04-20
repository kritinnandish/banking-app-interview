package com.ecom.cbdg.interview.application.repository;


import com.ecom.cbdg.interview.application.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByAccountType(String accountType);
    Account findByBankAccountNumber(String bankAccountNumber);
}

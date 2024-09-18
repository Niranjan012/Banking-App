package com.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
	
	// Custom query to find bank accounts by customer ID
    List<BankAccount> findByCustomerId(Long customerId);

    // Custom query to find bank accounts by status (open/locked)
    List<BankAccount> findByStatus(String status);

    // Custom query to find bank account by account number
    Optional<BankAccount> findByAccountNumber(String accountNumber);

}

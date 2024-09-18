package com.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.BankCustomer;

public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long>{
	
	// Custom query to find a customer by email
    Optional<BankCustomer> findByEmail(String email);

    // Custom query to check if a customer exists by email
    Boolean existsByEmail(String email);

    // Custom query to find active customers
    List<BankCustomer> findByIsActive(boolean isActive);

}

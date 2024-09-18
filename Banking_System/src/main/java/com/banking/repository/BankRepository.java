package com.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{
	
	// Custom query to find a bank by its name
    Optional<Bank> findByName(String name);

    // Custom query to check if a bank exists by name
    Boolean existsByName(String name);
    
}

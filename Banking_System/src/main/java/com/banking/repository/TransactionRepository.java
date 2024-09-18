package com.banking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
 // Custom query to find transactions by account ID and between two dates
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.transactionDate BETWEEN :startDate AND :endDate")
    List<Transaction> findByAccountIdAndTimeRange(
        @Param("accountId") Long accountId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );

}

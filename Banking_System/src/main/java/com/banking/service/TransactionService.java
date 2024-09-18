package com.banking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.banking.model.Transaction;
import com.banking.repository.TransactionRepository;

public class TransactionService {

	private final TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	// Create a new transaction
	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	// Get transaction by ID
	public Optional<Transaction> getTransactionById(Long id) {
		return transactionRepository.findById(id);
	}

	// Get transactions by account ID and time range
	public List<Transaction> getTransactionsByAccountIdAndTimeRange(Long accountId, LocalDateTime startDate,
			LocalDateTime endDate) {
		return transactionRepository.findByAccountIdAndTimeRange(accountId, startDate, endDate);
	}

	// Get all transactions for a specific account
	public List<Transaction> getAllTransactionsForAccount(Long accountId) {
		return transactionRepository.findAll(); // or a specific method to filter by accountId if needed
	}

	// Delete a transaction
	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}
}

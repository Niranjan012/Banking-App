package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.banking.model.Bank;
import com.banking.repository.BankRepository;

public class BankService {

	@Autowired
	private BankRepository bankRepository;

	public Bank addBank(Bank bank) {
		return bankRepository.save(bank);
	}

	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

	public Bank getBankById(Long id) {
		return bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));
	}

	public void deleteBank(Long id) {
		bankRepository.deleteById(id);
	}
}

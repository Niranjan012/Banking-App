package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.banking.model.BankAccount;
import com.banking.repository.BankAccountRepository;

public class AccountService {
	
	@Autowired
    private BankAccountRepository accountRepository;

    public BankAccount addAccount(BankAccount account) {
        return accountRepository.save(account);
    }

    public List<BankAccount> getAllAccounts() {
        return accountRepository.findAll();
    }

    public BankAccount getAccountById(Long id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    public void lockAccount(Long id) {
    	BankAccount account = getAccountById(id);
        account.setLocked(true);
        accountRepository.save(account);
    }

    public void unlockAccount(Long id) {
    	BankAccount account = getAccountById(id);
        account.setLocked(false);
        accountRepository.save(account);
    }
}

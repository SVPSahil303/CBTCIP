package com.Sahil.BankY.service;

import com.Sahil.BankY.model.Account;
import com.Sahil.BankY.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account createAccount(String ownerName) {
        Account account = new Account();
        account.setOwnerName(ownerName);
        account.setBalance(0.0);
        return accountRepository.save(account);
    }

    public Account createAccount(String ownerName, String username, String password) {
        Account account = new Account();
        account.setOwnerName(ownerName);
        account.setUsername(username);
        account.setPassword(password);
        account.setBalance(2000.0);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public void deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        transactionService.recordTransaction(accountId, amount, "deposit");
    }

    @Transactional
    public void withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        transactionService.recordTransaction(accountId, amount, "withdraw");
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, Double amount) {
        withdraw(fromAccountId, amount);
        deposit(toAccountId, amount);

        transactionService.recordTransaction(fromAccountId, amount, "transfer");
    }

    public Account login(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}

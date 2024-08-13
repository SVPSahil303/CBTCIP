package com.Sahil.BankY.service;
import com.Sahil.BankY.model.Account;
import com.Sahil.BankY.model.Transaction;
import com.Sahil.BankY.repository.AccountRepository;
import com.Sahil.BankY.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void recordTransaction(Long accountId, Double amount, String type) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDate(LocalDateTime.now());

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        transaction.setAccount(account);

        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}

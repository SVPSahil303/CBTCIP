package com.Sahil.BankY.controller;

import com.Sahil.BankY.model.Transaction;
import com.Sahil.BankY.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountId}")
    public String getTransactions(@PathVariable Long accountId, Model model) {
        List<Transaction> transactions = transactionService.getTransactions(accountId);
        model.addAttribute("transactions", transactions);
        return "transactions";

    }
}


package com.Sahil.BankY.controller;

import com.Sahil.BankY.model.Account;
import com.Sahil.BankY.model.TransferRequest;
import com.Sahil.BankY.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;


    @GetMapping("/{id}")
    public String getAccount(@PathVariable Long id, Model model) {
        Account account = accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        model.addAttribute("account", account);
        return "account-details";
    }

    @GetMapping("/{id}/deposit")
    public String showDepositForm(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "deposit";
    }

    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestParam Double amount) {
        accountService.deposit(id, amount);
        return "redirect:/" + id;
    }

    @GetMapping("/{id}/withdraw")
    public String showWithdrawForm(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "withdraw";
    }

    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestParam Double amount) {
        accountService.withdraw(id, amount);
        return "redirect:/" + id;
    }

    @GetMapping("/{id}/transfer")
    public String showTransferForm(@PathVariable Long id, Model model) {
        model.addAttribute("transferRequest", new TransferRequest());
        model.addAttribute("accountId", id);
        return "transfer";
    }

    @PostMapping("/{id}/transfer")
    public String transfer(@PathVariable Long id, @ModelAttribute TransferRequest transferRequest) {
        accountService.transfer(id, transferRequest.getToAccountId(), transferRequest.getAmount());
        return "redirect:/" + id;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Account account = accountService.login(username, password);
        if (account != null) {
            session.setAttribute("loggedInAccount", account);
            return "redirect:/" + account.getId();
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String ownerName) {
        Account account = accountService.createAccount(ownerName, username, password);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }



}

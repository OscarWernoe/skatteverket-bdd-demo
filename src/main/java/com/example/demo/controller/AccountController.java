package com.example.demo.controller;

import com.example.demo.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/checkBalance")
    public double checkBalance(String name, String pin) {
        return accountService.getBalance(name, pin);
    }

    @PostMapping("/withdraw")
    public double withdraw(@RequestParam String name, @RequestParam String pin, @RequestParam double amount) {
        return accountService.removeFromBalance(name, pin, amount);
    }

    @PostMapping("/deposit")
    public double deposit(@RequestParam String name, @RequestParam String pin, @RequestParam double amount) {
        return accountService.addToBalance(name, pin, amount);
    }

    // only purpose for this method is to perform the explicit authentication.feature tests
    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String name, @RequestParam String pin) {
        accountService.authenticate(name, pin);
        return "Welcome";
    }

}

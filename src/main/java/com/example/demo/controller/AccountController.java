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
    public Double checkBalance(String name, String pin) {
        return accountService.checkBalance();
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String name, @RequestParam String pin) {
        if (accountService.authenticate(name, pin)) {
            return "Welcome";
        } else {
            return "Not welcome";
        }
    }
}

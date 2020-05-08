package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.AuthenticationFailedException;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void authenticate(String name, String pin) {
        if (!accountRepository.existsByNameAndPin(name, pin)) {
            throw new AuthenticationFailedException();
        }
    }

    public double getBalance(String name, String pin) {
        authenticate(name, pin);
        Account account = accountRepository.findByNameAndPin(name, pin);
        return account.getBalance();
    }

    public double removeFromBalance(String name, String pin, double amount) {
        return updateAccountBalance(name, pin, -amount);
    }

    public double addToBalance(String name, String pin, double amount) {
        return updateAccountBalance(name, pin, amount);
    }

    private double updateAccountBalance(String name, String pin, double amount) {
        authenticate(name, pin);
        Account account = accountRepository.findByNameAndPin(name, pin);
        account.addToBalance(amount);
        accountRepository.save(account);
        return account.getBalance();
    }
}

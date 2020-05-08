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

    public boolean authenticate(String name, String pin) {
        if (nameAndPinMatch(name, pin)) {
           return true;
        } else {
            throw new AuthenticationFailedException();
        }
    }

    public double checkBalance(String name, String pin) {
        if (authenticate(name, pin)) {
            Account account = accountRepository.findByNameAndPin(name, pin);
            return account.getBalance();
        } else {
            throw new AuthenticationFailedException();
        }
    }

    private boolean nameAndPinMatch(String name, String pin) {
        return accountRepository.existsByNameAndPin(name, pin);
    }
}

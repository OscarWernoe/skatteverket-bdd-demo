package com.example.demo.service;

import com.example.demo.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private Account account = new Account("Bob", "1234", 100);

    public boolean authenticate(String name, String pin) {
        return name.equals(account.getName()) && pin.equals(account.getPin());
    }

    public double checkBalance() {
        return account.getBalance();
    }
}

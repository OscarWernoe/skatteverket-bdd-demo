package com.example.demo;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public Bootstrap(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {
        accountRepository.save(new Account("Bob", "1111", 0));
        accountRepository.save(new Account("Alice", "2222", 0));
        accountRepository.save(new Account("Eve", "3333", 0));
    }
}

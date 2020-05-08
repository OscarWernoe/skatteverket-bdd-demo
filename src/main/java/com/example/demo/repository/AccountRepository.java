package com.example.demo.repository;

import com.example.demo.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    boolean existsByNameAndPin(String name, String pin);

    Account findByNameAndPin(String name, String pin);
}

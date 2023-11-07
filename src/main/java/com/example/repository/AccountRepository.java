package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    // This method retrieves an account by username
    Account getAccountByUsername(String username);


}

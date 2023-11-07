package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    // This method retrieves an account by username
    Account getAccountByUsername(String username);

    // Retrieves all usernames in the db
    @Query("SELECT username FROM Account")
    List<String> getAllUsernames();
}

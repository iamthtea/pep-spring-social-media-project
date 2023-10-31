package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    // This method retrieves all accounts in the database.
    @Query("FROM Account")
    List<Account> getAllAccounts();

    // This method adds an account to the database (and generates an account id)
    @Query("INSERT INTO Account (username, password) VALUES (:user, :pword)")
    Account addAccount(@Param("user") String username, @Param("pword") String password);

    // This method retrieves an account by username
    @Query("FROM Account WHERE username = :username")
    Account getAccountByUsername(@Param("username") String username);

    // This method retrieves an account by user id
    @Query("FROM Account WHERE id = :userId")
    Account getAccountById(@Param("userId") Integer id);

}

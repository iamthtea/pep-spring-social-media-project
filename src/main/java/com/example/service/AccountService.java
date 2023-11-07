package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    // Create a new account. Username must be unique and not null. Password must be > 4 characters
    public Account createAccount(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        Account accountSearch = accountRepository.getAccountByUsername(username);
        if (username != null && accountSearch == null) {
            if (password.length() > 4) {
                return accountRepository.save(account);
            }
        } else if (accountSearch != null) {

        }
        return null;
    }

    // User Login. Username must exist in DB, password must match
    public Account authAccount(Account account) {
        String uName = account.getUsername();
        String pWord = account.getPassword();
        List<String> users = accountRepository.getAllUsernames();
        if (users.contains(uName)) {
            Account accMatch = accountRepository.getAccountByUsername(uName);
            if (pWord.equals(accMatch.getPassword())) {
                return accMatch;
            }
        }
        return null;
    }

    // Get all username
    public List<String> getAllUsernames() {
        return accountRepository.getAllUsernames();
    }

}

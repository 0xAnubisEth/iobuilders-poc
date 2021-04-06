package com.roman.wallet.accounts.application.search_by_user;

import com.roman.wallet.accounts.domain.Account;
import com.roman.wallet.accounts.domain.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class AccountFinder {
    private final AccountRepository repository;

    public AccountFinder(AccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> find(String userId) {
        return repository.findByUserId(userId);
    }
}

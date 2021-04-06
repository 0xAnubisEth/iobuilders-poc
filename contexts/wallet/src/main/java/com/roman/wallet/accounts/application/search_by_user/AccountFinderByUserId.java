package com.roman.wallet.accounts.application.search_by_user;

import com.roman.wallet.accounts.domain.Account;
import com.roman.wallet.accounts.domain.AccountRepository;
import com.roman.wallet.accounts.domain.AccountUserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class AccountFinderByUserId {
    private final AccountRepository repository;

    public AccountFinderByUserId(AccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> find(String userId) {
        return repository.findByUserId(new AccountUserId(userId));
    }
}

package com.roman.wallet.accounts.domain;

import com.roman.shared.domain.Identifier;

import java.util.Optional;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findByUserId(AccountUserId userId);
    Optional<Account> findById(Identifier id);
}

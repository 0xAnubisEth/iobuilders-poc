package com.roman.wallet.users.application;

import com.roman.wallet.users.domain.WalletUser;
import com.roman.wallet.users.domain.WalletUserRepository;
import org.springframework.stereotype.Service;

@Service
public final class WalletUserCreator {
    private final WalletUserRepository repository;

    public WalletUserCreator(WalletUserRepository repository) {
        this.repository = repository;
    }

    public void create(String id, String username, String name, String lastname) {
        this.repository.save(new WalletUser(id, username, name, lastname));
    }
}

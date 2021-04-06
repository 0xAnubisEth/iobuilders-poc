package com.roman.wallet.accounts.application.create;

import com.roman.shared.domain.bus.event.EventBus;
import com.roman.wallet.accounts.domain.Account;
import com.roman.wallet.accounts.domain.AccountRepository;

public class AccountCreator {
    private final AccountRepository repository;
    private final EventBus eventBus;

    public AccountCreator(AccountRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(String id, String userId) {
        Account account = Account.create(id, userId);

        repository.save(account);

        eventBus.publish(account.pullDomainEvents());
    }
}

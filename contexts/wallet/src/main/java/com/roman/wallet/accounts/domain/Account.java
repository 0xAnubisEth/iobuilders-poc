package com.roman.wallet.accounts.domain;

import com.roman.shared.domain.AggregateRoot;

public final class Account extends AggregateRoot {
    private final AccountId id;
    private final AccountUserId userId;
    private final AccountBalance balance;

    public Account() {
        this.id = null;
        this.userId = null;
        this.balance = null;
    }

    public Account(AccountId id, AccountUserId userId, AccountBalance balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public static Account create(String id, String userId) {
        Account account = new Account(new AccountId(id), new AccountUserId(userId), new AccountBalance((float) 0));

        AccountCreated accountCreated = new AccountCreated(id, userId, (float) 0);

        account.record(accountCreated);

        return account;
    }

    public AccountId id() {
        return id;
    }

    public AccountUserId userId() {
        return userId;
    }

    public AccountBalance balance() {
        return balance;
    }
}

package com.roman.wallet.accounts.application;

import com.roman.shared.domain.bus.query.Response;

public class AccountResponse implements Response {
    private final String id;
    private final String userId;
    private final Float balance;

    public AccountResponse(String id, String userId, Float balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public String id() {
        return id;
    }

    public String userId() {
        return userId;
    }

    public Float balance() {
        return balance;
    }
}

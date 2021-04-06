package com.roman.wallet.accounts.application.search_by_user;

import com.roman.shared.domain.bus.query.Query;

public class SearchAccountByUserQuery implements Query {
    private final String userId;

    public SearchAccountByUserQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}

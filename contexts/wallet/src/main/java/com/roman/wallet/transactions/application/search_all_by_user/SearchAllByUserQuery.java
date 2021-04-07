package com.roman.wallet.transactions.application.search_all_by_user;

import com.roman.shared.domain.bus.query.Query;

public class SearchAllByUserQuery implements Query {
    private final String userId;

    public SearchAllByUserQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}

package com.roman.wallet.accounts.application.search_by_id;

import com.roman.shared.domain.bus.query.Query;

public final class SearchAccountByIdQuery implements Query {
    private final String id;

    public SearchAccountByIdQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}

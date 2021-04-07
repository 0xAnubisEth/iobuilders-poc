package com.roman.shared.application.auth;

import com.roman.shared.domain.bus.query.Query;

public class SearchTokenQuery implements Query {
    private final String userId;

    public SearchTokenQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}

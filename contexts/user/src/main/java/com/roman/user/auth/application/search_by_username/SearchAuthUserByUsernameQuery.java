package com.roman.user.auth.application.search_by_username;

import com.roman.shared.domain.bus.query.Query;

public class SearchAuthUserByUsernameQuery implements Query {
    private final String username;

    public SearchAuthUserByUsernameQuery(String username) {
        this.username = username;
    }

    public String username() {
        return username;
    }
}

package com.roman.user.users.application.search_by_username;

import com.roman.shared.domain.bus.query.Query;

public class SearchByUsernameQuery implements Query {
    private final String username;

    public SearchByUsernameQuery(String username) {
        this.username = username;
    }

    public String username() {
        return username;
    }
}

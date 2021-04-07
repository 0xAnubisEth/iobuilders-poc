package com.roman.shared.application.auth;

import com.roman.shared.domain.bus.query.Response;

public class UserAuthResponse implements Response {
    private final String userId;
    private final String username;
    private final String token;

    public UserAuthResponse(String userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }

    public String userId() {
        return userId;
    }

    public String username() {
        return username;
    }

    public String token() {
        return token;
    }
}

package com.roman.user.auth.domain;

public class AuthUser {
    private final String userId;
    private final String username;
    private final String token;

    public AuthUser(String userId, String username, String token) {
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

package com.roman.user.auth.domain;

import java.io.Serializable;

public class AuthUser implements Serializable {
    public final String userId;
    public final String username;
    public final String token;

    public AuthUser(String userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }
}

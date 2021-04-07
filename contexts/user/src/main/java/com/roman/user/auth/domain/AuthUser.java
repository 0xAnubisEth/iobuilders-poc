package com.roman.user.auth.domain;

import java.io.Serializable;

public class AuthUser implements Serializable {
    public String userId;
    public String username;
    public String token;

    public AuthUser(String userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }

    public AuthUser() {
        this.userId = null;
        this.username = null;
        this.token = null;
    }
}

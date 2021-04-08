package com.roman.user.users.application;

import com.roman.shared.domain.bus.query.Response;

public class UserResponse implements Response {
    private final String userId;
    private final String username;
    private final String password;
    private final String name;
    private final String lastname;

    public UserResponse(String userId, String username, String password, String name, String lastname) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }

    public String userId() {
        return userId;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String name() {
        return name;
    }

    public String lastname() {
        return lastname;
    }
}

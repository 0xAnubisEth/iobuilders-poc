package com.roman.user.users.domain;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("The user with username <%s> not exists", username));
    }
}

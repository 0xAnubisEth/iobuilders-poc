package com.roman.user.users.domain;

import com.roman.shared.domain.DomainError;

public class UserNotFoundError extends DomainError {
    public UserNotFoundError(String username) {
        super("404", String.format("The user with username <%s> not exists", username));
    }
}

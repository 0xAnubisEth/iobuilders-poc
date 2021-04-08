package com.roman.user.users.domain;

import com.roman.shared.domain.DomainError;

public class UserAlreadyExistsError extends DomainError {
    public UserAlreadyExistsError(String username) {
        super("409", String.format("The user with the user name <%s> already exists", username));
    }
}

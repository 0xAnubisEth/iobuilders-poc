package com.roman.user.auth.application.login;

import com.roman.shared.domain.DomainError;

public class UnauthorizedError extends DomainError {
    public UnauthorizedError() {
        super("403", "Unauthorized error");
    }
}

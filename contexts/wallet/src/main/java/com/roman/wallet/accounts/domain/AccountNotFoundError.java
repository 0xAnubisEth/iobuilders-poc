package com.roman.wallet.accounts.domain;

import com.roman.shared.domain.DomainError;

public class AccountNotFoundError extends DomainError {
    public AccountNotFoundError(String errorMessage) {
        super("404", errorMessage);
    }
}

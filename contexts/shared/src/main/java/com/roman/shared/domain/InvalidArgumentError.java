package com.roman.shared.domain;

public class InvalidArgumentError extends DomainError {
    public InvalidArgumentError(String message) {
        super("400", message);
    }
}

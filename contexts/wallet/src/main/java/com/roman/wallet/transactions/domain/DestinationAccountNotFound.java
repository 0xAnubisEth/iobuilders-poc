package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.DomainError;

public class DestinationAccountNotFound extends DomainError {
    public DestinationAccountNotFound(String destination) {
        super("404", String.format("<%s> destination account does not exist", destination));
    }
}

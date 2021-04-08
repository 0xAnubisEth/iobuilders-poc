package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.DomainError;

public class AccountHasNotBalanceError extends DomainError {
    public AccountHasNotBalanceError(Float quantity) {
        super("409", String.format("The originating account does not have a <%s> balance", quantity));
    }
}

package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.StringValueObject;

public class TransactionDestination extends StringValueObject {
    public TransactionDestination(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public TransactionDestination() {
        super("");
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Destination is required");
        }
    }
}

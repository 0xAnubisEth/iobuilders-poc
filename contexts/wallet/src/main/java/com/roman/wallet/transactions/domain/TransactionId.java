package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.Identifier;
import com.roman.shared.domain.InvalidArgumentError;

public class TransactionId extends Identifier {
    public TransactionId(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public TransactionId() {
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Id is required");
        }
    }
}

package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.StringValueObject;

public class TransactionType extends StringValueObject {
    public TransactionType(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public TransactionType() {
        super("");
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Type is required");
        }
    }
}

package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.StringValueObject;

public class TransactionOrigin extends StringValueObject {
    public TransactionOrigin(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public TransactionOrigin() {
        super("");
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Origin is required");
        }
    }
}

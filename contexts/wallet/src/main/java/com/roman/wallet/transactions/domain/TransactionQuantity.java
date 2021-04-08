package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.FloatValueObject;
import com.roman.shared.domain.InvalidArgumentError;

public class TransactionQuantity extends FloatValueObject {
    public TransactionQuantity(Float value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public TransactionQuantity() {
        super((float) 0);
    }

    private void ensureRequiredArgument(Float value) {
        if (value == null) {
            throw new InvalidArgumentError("Quantity is required");
        }
    }
}

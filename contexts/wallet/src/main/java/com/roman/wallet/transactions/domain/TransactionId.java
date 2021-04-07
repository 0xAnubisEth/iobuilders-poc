package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.Identifier;

public class TransactionId extends Identifier {
    public TransactionId(String value) {
        super(value);
    }

    public TransactionId() {
    }
}

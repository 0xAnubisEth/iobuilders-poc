package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.StringValueObject;

public class TransactionType extends StringValueObject {
    public TransactionType(String value) {
        super(value);
    }

    public TransactionType() {
        super("");
    }
}

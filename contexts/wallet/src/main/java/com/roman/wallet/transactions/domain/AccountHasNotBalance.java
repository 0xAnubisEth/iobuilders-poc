package com.roman.wallet.transactions.domain;

public class AccountHasNotBalance extends RuntimeException {
    public AccountHasNotBalance(Float quantity) {
        super(String.format("The originating account does not have a <%s> balance", quantity));
    }
}

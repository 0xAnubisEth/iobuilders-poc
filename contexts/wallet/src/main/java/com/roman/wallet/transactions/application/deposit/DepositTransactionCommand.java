package com.roman.wallet.transactions.application.deposit;

import com.roman.shared.domain.bus.command.Command;

public final class DepositTransactionCommand implements Command {
    private final String id;
    private final String userId;
    private final Float quantity;
    private final String concept;

    public DepositTransactionCommand(String id, String userId, Float quantity, String concept) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.concept = concept;
    }

    public String id() {
        return id;
    }

    public String userId() {
        return userId;
    }

    public Float quantity() {
        return quantity;
    }

    public String concept() {
        return concept;
    }
}

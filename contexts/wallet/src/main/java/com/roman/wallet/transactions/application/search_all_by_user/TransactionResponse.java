package com.roman.wallet.transactions.application.search_all_by_user;

import com.roman.shared.domain.bus.query.Response;

public class TransactionResponse implements Response {
    private final String concept;
    private final String origin;
    private final String destination;
    private final Float quantity;
    private final String type;

    public TransactionResponse(String concept, String origin, String destination, Float quantity, String type) {
        this.concept = concept;
        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
        this.type = type;
    }

    public String concept() {
        return concept;
    }

    public String origin() {
        return origin;
    }

    public String destination() {
        return destination;
    }

    public Float quantity() {
        return quantity;
    }

    public String type() {
        return type;
    }
}

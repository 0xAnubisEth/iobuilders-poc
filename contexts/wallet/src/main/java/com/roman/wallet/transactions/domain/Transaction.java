package com.roman.wallet.transactions.domain;

import com.roman.shared.domain.AggregateRoot;
import com.roman.shared.domain.bus.event.DomainEvent;

public class Transaction extends AggregateRoot {
    private final TransactionId id;
    private final TransactionOrigin origin;
    private final TransactionDestination destination;
    private final TransactionQuantity quantity;
    private final TransactionType type;
    private final TransactionConcept concept;

    public Transaction() {
        this.id = null;
        this.origin = null;
        this.destination = null;
        this.quantity = null;
        this.type = null;
        this.concept = null;
    }

    public Transaction(TransactionId id, TransactionOrigin origin, TransactionDestination destination, TransactionQuantity quantity, TransactionType type, TransactionConcept concept) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
        this.type = type;
        this.concept = concept;
    }

    public static Transaction create(String id, String origin, String destination, Float quantity, String type, String concept) {
        return new Transaction(new TransactionId(id), new TransactionOrigin(origin), new TransactionDestination(destination), new TransactionQuantity(quantity), new TransactionType(type), new TransactionConcept(concept));
    }

    public void recordEvent(DomainEvent event) {
        this.record(event);
    }

    public TransactionId id() {
        return id;
    }

    public TransactionOrigin origin() {
        return origin;
    }

    public TransactionDestination destination() {
        return destination;
    }

    public TransactionQuantity quantity() {
        return quantity;
    }

    public TransactionType type() {
        return type;
    }

    public TransactionConcept concept() {
        return concept;
    }
}

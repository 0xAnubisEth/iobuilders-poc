package com.roman.wallet.transactions.domain.bus.event;

import com.roman.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class TransferTransactionDomainEvent extends DomainEvent {
    private final String origin;
    private final String destination;
    private final Float quantity;
    private final String type;
    private final String concept;

    public TransferTransactionDomainEvent() {
        this.origin = null;
        this.destination = null;
        this.quantity = null;
        this.type = null;
        this.concept = null;
    }

    public TransferTransactionDomainEvent(String aggregateId, String eventId, String occurredOn, String origin, String destination, Float quantity, String type, String concept) {
        super(aggregateId, eventId, occurredOn);

        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
        this.type = type;
        this.concept = concept;
    }

    public TransferTransactionDomainEvent(String aggregateId, String origin, String destination, Float quantity, String type, String concept) {
        super(aggregateId);

        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
        this.type = type;
        this.concept = concept;
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

    public String concept() {
        return concept;
    }

    @Override
    public String eventName() {
        return "transaction.transfer";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("origin", origin);
            put("destination", destination);
            put("quantity", quantity);
            put("type", type);
            put("concept", concept);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new TransferTransactionDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("origin"),
                (String) body.get("destination"),
                Float.parseFloat(body.get("quantity").toString()),
                (String) body.get("type"),
                (String) body.get("concept")
        );
    }
}

package com.roman.wallet.accounts.domain;

import com.roman.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class AccountCreated extends DomainEvent {

    private final String id;
    private final String userId;
    private final Float balance;

    public AccountCreated() {
        this.id = null;
        this.userId = null;
        this.balance = null;
    }

    public AccountCreated(String id, String userId, Float balance) {
        super(null);

        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public AccountCreated(String aggregateId, String id, String userId, Float balance) {
        super(aggregateId);

        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public AccountCreated(String aggregateId, String eventId, String occurredOn, String id, String userId, Float balance) {
        super(aggregateId, eventId, occurredOn);

        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public String id() {
        return id;
    }

    public String userId() {
        return userId;
    }

    public Float balance() {
        return balance;
    }

    @Override
    public String eventName() {
        return "account.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {
            {
                put("id", id);
                put("userId", userId);
                put("balance", balance);
            }
        };
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new AccountCreated(aggregateId, eventId, occurredOn, (String) body.get("id"), (String) body.get("userId"), (Float) body.get("balance"));
    }
}

package com.roman.user.users.domain;

import com.roman.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class UserCreated extends DomainEvent {
    private final String id;
    private final String username;
    private final String password;

    public UserCreated() {
        super(null);

        this.id = null;
        this.username = null;
        this.password = null;
    }

    public UserCreated(String id, String username, String password) {
        super(null);

        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserCreated(String aggregateId, String id, String username, String password) {
        super(aggregateId);

        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserCreated(String aggregateId, String eventId, String occurredOn, String id, String username, String password) {
        super(aggregateId, eventId, occurredOn);

        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String eventName() {
        return "user.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {
            {
                put("id", id);
                put("username", username);
                put("password", password);
            }
        };
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new UserCreated(aggregateId, eventId, occurredOn, (String) body.get("id"), (String) body.get("username"), (String) body.get("password"));
    }

    public String id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}

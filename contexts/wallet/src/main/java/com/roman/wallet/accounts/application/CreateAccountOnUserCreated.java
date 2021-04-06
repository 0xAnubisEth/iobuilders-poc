package com.roman.wallet.accounts.application;

import com.roman.shared.domain.bus.event.DomainEventSubscriber;
import com.roman.user.users.domain.UserCreated;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@DomainEventSubscriber({UserCreated.class})
public final class CreateAccountOnUserCreated {
    private final AccountCreator creator;

    public CreateAccountOnUserCreated(AccountCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(UserCreated event) {
        creator.create(UUID.randomUUID().toString(), event.aggregateId());
    }
}

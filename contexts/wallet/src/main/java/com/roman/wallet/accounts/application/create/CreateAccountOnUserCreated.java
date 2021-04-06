package com.roman.wallet.accounts.application.create;

import com.roman.shared.domain.bus.event.DomainEventSubscriber;
import com.roman.shared.domain.user.UserCreatedDomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@DomainEventSubscriber({UserCreatedDomainEvent.class})
public final class CreateAccountOnUserCreated {
    private final AccountCreator creator;

    public CreateAccountOnUserCreated(AccountCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(UserCreatedDomainEvent event) {
        creator.create(UUID.randomUUID().toString(), event.aggregateId());
    }
}

package com.roman.wallet.users.application;

import com.roman.shared.domain.bus.event.DomainEventSubscriber;
import com.roman.shared.domain.user.UserCreatedDomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@DomainEventSubscriber({UserCreatedDomainEvent.class})
public final class CreateWalletUserOnUserCreate {
    private final WalletUserCreator creator;

    public CreateWalletUserOnUserCreate(WalletUserCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(UserCreatedDomainEvent event) {
        creator.create(event.aggregateId(), event.username(), event.name(), event.lastname());
    }
}

package com.roman.wallet.accounts.application.decrement_balance;

import com.roman.shared.domain.bus.event.DomainEventSubscriber;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.transactions.domain.bus.event.WithdrawTransactionDomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@DomainEventSubscriber({WithdrawTransactionDomainEvent.class})
public class DecrementBalanceOnTransactionCreated {
    private final BalanceDecrementer decrementer;

    public DecrementBalanceOnTransactionCreated(BalanceDecrementer decrementer) {
        this.decrementer = decrementer;
    }

    @EventListener
    public void on(WithdrawTransactionDomainEvent event) throws QueryHandlerExecutionError {
        decrementer.decrement(event.destination(), +event.quantity());
    }
}

package com.roman.wallet.accounts.application.increment_balance;

import com.roman.shared.domain.bus.event.DomainEventSubscriber;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.transactions.domain.bus.event.DepositTransactionDomainEvent;
import com.roman.wallet.transactions.domain.bus.event.TransferTransactionDomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@DomainEventSubscriber({
        DepositTransactionDomainEvent.class,
        TransferTransactionDomainEvent.class
})
public class IncrementerBalanceOnTransactionCreated {
    private final BalanceIncrementer incrementer;

    public IncrementerBalanceOnTransactionCreated(BalanceIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @EventListener
    public void on(DepositTransactionDomainEvent event) throws QueryHandlerExecutionError {
        incrementer.increment(event.destination(), +event.quantity());
    }

    @EventListener
    public void on(TransferTransactionDomainEvent event) throws QueryHandlerExecutionError {
        incrementer.increment(event.destination(), +event.quantity());
    }
}

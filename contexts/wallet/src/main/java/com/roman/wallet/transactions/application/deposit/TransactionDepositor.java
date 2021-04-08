package com.roman.wallet.transactions.application.deposit;

import com.roman.shared.domain.bus.event.EventBus;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.application.search_by_user.SearchAccountByUserQuery;
import com.roman.wallet.transactions.domain.bus.event.DepositTransactionDomainEvent;
import com.roman.wallet.transactions.domain.Transaction;
import com.roman.wallet.transactions.domain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public final class TransactionDepositor {
    private final String TRANSACTION_TYPE = "Deposit";
    private final TransactionRepository repository;
    private final EventBus eventBus;
    private final QueryBus queryBus;

    public TransactionDepositor(TransactionRepository repository, EventBus eventBus, QueryBus queryBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.queryBus = queryBus;
    }

    public void deposit(String id, String concept, Float quantity, String userId) throws QueryHandlerExecutionError {
        // Search origin account
        AccountResponse response = queryBus.ask(new SearchAccountByUserQuery(userId));

        // Create transaction
        Transaction transaction = Transaction.create(id, response.id(), response.id(), quantity, TRANSACTION_TYPE, concept);

        // Record event
        transaction.recordEvent(new DepositTransactionDomainEvent(id, response.id(), response.id(), quantity, TRANSACTION_TYPE, concept));

        // Save in database
        repository.save(transaction);

        // Publish events
        eventBus.publish(transaction.pullDomainEvents());
    }
}

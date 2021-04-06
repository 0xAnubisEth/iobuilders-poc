package com.roman.wallet.transactions.application.withdraw;

import com.roman.shared.domain.bus.event.EventBus;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.application.search_by_user.SearchAccountByUserQuery;
import com.roman.wallet.transactions.domain.Transaction;
import com.roman.wallet.transactions.domain.TransactionRepository;
import com.roman.wallet.transactions.domain.bus.event.WithdrawTransactionDomainEvent;
import org.springframework.stereotype.Service;

@Service
public final class TransactionWithdraw {
    private final String TRANSACTION_TYPE = "Withdraw";
    private final TransactionRepository repository;
    private final EventBus eventBus;
    private final QueryBus queryBus;

    public TransactionWithdraw(TransactionRepository repository, EventBus eventBus, QueryBus queryBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.queryBus = queryBus;
    }

    public void withdraw(String id, String userId, Float quantity, String concept) throws QueryHandlerExecutionError {
        // Search origin account
        AccountResponse response = queryBus.ask(new SearchAccountByUserQuery(userId));

        // Save transaction
        Transaction transaction = Transaction.create(id, response.id(), response.id(), -quantity, TRANSACTION_TYPE, concept);
        repository.save(transaction);

        // Save event
        transaction.recordEvent(new WithdrawTransactionDomainEvent(id, response.id(), response.id(), quantity, TRANSACTION_TYPE, concept));

        // Publish event
        eventBus.publish(transaction.pullDomainEvents());
    }
}

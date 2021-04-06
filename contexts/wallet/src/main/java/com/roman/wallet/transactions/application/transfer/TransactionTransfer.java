package com.roman.wallet.transactions.application.transfer;

import com.roman.shared.domain.bus.event.EventBus;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.application.search_by_id.SearchAccountByIdQuery;
import com.roman.wallet.accounts.application.search_by_user.SearchAccountByUserQuery;
import com.roman.wallet.transactions.domain.Transaction;
import com.roman.wallet.transactions.domain.TransactionRepository;
import com.roman.wallet.transactions.domain.TransferTransactionDomainEvent;
import org.springframework.stereotype.Service;

@Service
public final class TransactionTransfer {
    private final String TRANSACTION_TYPE = "Transfer";
    private final TransactionRepository repository;
    private final EventBus eventBus;
    private final QueryBus queryBus;

    public TransactionTransfer(TransactionRepository repository, EventBus eventBus, QueryBus queryBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.queryBus = queryBus;
    }

    public void transfer(String id, String userId, String destination, Float quantity, String concept) throws QueryHandlerExecutionError {
        // Search origin account
        AccountResponse originResponse = queryBus.ask(new SearchAccountByUserQuery(userId));

        // Search destination account
        AccountResponse destinationResponse = queryBus.ask(new SearchAccountByIdQuery(destination));

        // Save transaction
        Transaction transaction = Transaction.create(id, originResponse.id(), destinationResponse.id(), quantity, TRANSACTION_TYPE, concept);
        repository.save(transaction);

        // Save event
        transaction.recordEvent(new TransferTransactionDomainEvent(id, originResponse.id(), destinationResponse.id(), quantity, TRANSACTION_TYPE, concept));

        // Publish event
        eventBus.publish(transaction.pullDomainEvents());
    }
}

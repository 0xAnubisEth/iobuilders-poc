package com.roman.wallet.transactions.application.transfer;

import com.roman.shared.domain.bus.command.CommandBus;
import com.roman.shared.domain.bus.command.CommandHandlerExecutionError;
import com.roman.shared.domain.bus.event.EventBus;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.application.search_by_id.SearchAccountByIdQuery;
import com.roman.wallet.accounts.application.search_by_user.SearchAccountByUserQuery;
import com.roman.wallet.transactions.application.withdraw.WithdrawTransactionCommand;
import com.roman.wallet.transactions.domain.AccountHasNotBalance;
import com.roman.wallet.transactions.domain.Transaction;
import com.roman.wallet.transactions.domain.TransactionRepository;
import com.roman.wallet.transactions.domain.bus.event.TransferTransactionDomainEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class TransactionTransfer {
    private final String TRANSACTION_TYPE = "Transfer";
    private final TransactionRepository repository;
    private final EventBus eventBus;
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    public TransactionTransfer(TransactionRepository repository, EventBus eventBus, QueryBus queryBus, CommandBus commandBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    public void transfer(String id, String userId, String destination, Float quantity, String concept) throws QueryHandlerExecutionError, CommandHandlerExecutionError {
        // Search origin account
        AccountResponse originResponse = queryBus.ask(new SearchAccountByUserQuery(userId));

        if (originResponse.balance() < quantity) {
            throw new AccountHasNotBalance(quantity);
        }

        // Search destination account
        AccountResponse destinationResponse = queryBus.ask(new SearchAccountByIdQuery(destination));

        // Save destination transaction
        Transaction destinationTransaction = Transaction.create(id, originResponse.id(), destinationResponse.id(), quantity, TRANSACTION_TYPE, concept);
        repository.save(destinationTransaction);

        // Save origin transaction
        commandBus.dispatch(new WithdrawTransactionCommand(UUID.randomUUID().toString(), userId, quantity, String.format("Transfer ID: <%s> - Destination account ID: <%s>", id, destination)));

        // Save event
        destinationTransaction.recordEvent(new TransferTransactionDomainEvent(id, originResponse.id(), destinationResponse.id(), quantity, TRANSACTION_TYPE, concept));

        // Publish event
        eventBus.publish(destinationTransaction.pullDomainEvents());
    }
}

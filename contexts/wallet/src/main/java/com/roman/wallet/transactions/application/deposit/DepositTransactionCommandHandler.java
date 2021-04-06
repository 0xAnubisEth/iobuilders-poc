package com.roman.wallet.transactions.application.deposit;

import com.roman.shared.domain.bus.command.CommandHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Service;

@Service
public final class DepositTransactionCommandHandler implements CommandHandler<DepositTransactionCommand> {
    private final TransactionDepositor depositor;

    public DepositTransactionCommandHandler(TransactionDepositor depositor) {
        this.depositor = depositor;
    }

    @Override
    public void handle(DepositTransactionCommand command) throws QueryHandlerExecutionError {
        depositor.deposit(command.id(), command.concept(), command.quantity(), command.userId());
    }
}

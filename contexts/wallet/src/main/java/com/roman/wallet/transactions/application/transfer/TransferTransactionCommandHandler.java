package com.roman.wallet.transactions.application.transfer;

import com.roman.shared.domain.bus.command.CommandHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Service;

@Service
public final class TransferTransactionCommandHandler implements CommandHandler<TransferTransactionCommand> {
    @Override
    public void handle(TransferTransactionCommand command) throws QueryHandlerExecutionError {

    }
}

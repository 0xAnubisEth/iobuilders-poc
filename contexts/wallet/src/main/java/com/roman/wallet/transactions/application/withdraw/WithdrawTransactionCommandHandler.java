package com.roman.wallet.transactions.application.withdraw;

import com.roman.shared.domain.bus.command.CommandHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Service;

@Service
public final class WithdrawTransactionCommandHandler implements CommandHandler<WithdrawTransactionCommand> {
    @Override
    public void handle(WithdrawTransactionCommand command) throws QueryHandlerExecutionError {
        
    }
}

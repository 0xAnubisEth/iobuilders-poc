package com.roman.wallet.transactions.application.withdraw;

import com.roman.shared.domain.bus.command.CommandHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Service;

@Service
public final class WithdrawTransactionCommandHandler implements CommandHandler<WithdrawTransactionCommand> {
    private final TransactionWithdraw withdraw;

    public WithdrawTransactionCommandHandler(TransactionWithdraw withdraw) {
        this.withdraw = withdraw;
    }

    @Override
    public void handle(WithdrawTransactionCommand command) throws QueryHandlerExecutionError {
        withdraw.withdraw(command.id(), command.userId(), command.quantity(), command.concept());
    }
}

package com.roman.wallet.accounts.application.increment_balance;

import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.application.search_by_id.SearchAccountByIdQuery;
import com.roman.wallet.accounts.domain.*;
import org.springframework.stereotype.Service;

@Service
public class BalanceIncrementer {
    private final AccountRepository repository;
    private final QueryBus queryBus;

    public BalanceIncrementer(AccountRepository repository, QueryBus queryBus) {
        this.repository = repository;
        this.queryBus = queryBus;
    }

    public void increment(String accountId, Float quantity) throws QueryHandlerExecutionError {
        // Get account
        AccountResponse response = queryBus.ask(new SearchAccountByIdQuery(accountId));

        // Modify balance
        Account account = new Account(new AccountId(response.id()), new AccountUserId(response.userId()), new AccountBalance(response.balance() + quantity));

        // Save account
        repository.save(account);
    }
}

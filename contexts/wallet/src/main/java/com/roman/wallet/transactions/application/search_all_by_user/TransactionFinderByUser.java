package com.roman.wallet.transactions.application.search_all_by_user;

import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.application.search_by_user.SearchAccountByUserQuery;
import com.roman.wallet.transactions.domain.Transaction;
import com.roman.wallet.transactions.domain.TransactionAccountId;
import com.roman.wallet.transactions.domain.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionFinderByUser {
    private final TransactionRepository repository;
    private final QueryBus queryBus;

    public TransactionFinderByUser(TransactionRepository repository, QueryBus queryBus) {
        this.repository = repository;
        this.queryBus = queryBus;
    }

    List<Transaction> find(String userId) throws QueryHandlerExecutionError {
        // Get account by userId
        AccountResponse accountResponse = queryBus.ask(new SearchAccountByUserQuery(userId));

        // Get result list
        return repository.getAll(new TransactionAccountId(accountResponse.id()));
    }
}

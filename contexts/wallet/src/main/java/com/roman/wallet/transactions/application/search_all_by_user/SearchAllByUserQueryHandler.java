package com.roman.wallet.transactions.application.search_all_by_user;

import com.roman.shared.domain.bus.query.QueryHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.wallet.transactions.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchAllByUserQueryHandler implements QueryHandler<SearchAllByUserQuery, TransactionsResponse> {
    private final TransactionFinderByUser finder;

    public SearchAllByUserQueryHandler(TransactionFinderByUser finder) {
        this.finder = finder;
    }

    @Override
    public TransactionsResponse handle(SearchAllByUserQuery query) throws QueryHandlerExecutionError {
        List<Transaction> transactions = finder.find(query.userId());
        return new TransactionsResponse(transactions.stream().map(transaction -> new TransactionResponse(
                transaction.concept().value(),
                transaction.origin().value(),
                transaction.destination().value(),
                transaction.quantity().value(),
                transaction.type().value())
        ).collect(Collectors.toList()));
    }
}

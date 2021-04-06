package com.roman.wallet.accounts.application.search_by_id;

import com.roman.shared.domain.bus.query.QueryHandler;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.domain.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class SearchAccountByIdQueryHandler implements QueryHandler<SearchAccountByIdQuery, AccountResponse> {
    private final AccountFinder finder;

    public SearchAccountByIdQueryHandler(AccountFinder finder) {
        this.finder = finder;
    }

    @Override
    public AccountResponse handle(SearchAccountByIdQuery query) {
        Optional<Account> optional = finder.find(query.id());
        if (optional.isPresent()) {
            Account account = optional.get();
            return new AccountResponse(account.id().value(), account.userId().value(), account.balance().value());
        }
        return null;
    }
}

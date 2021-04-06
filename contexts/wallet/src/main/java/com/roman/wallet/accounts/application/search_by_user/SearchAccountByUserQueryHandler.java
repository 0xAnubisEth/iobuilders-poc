package com.roman.wallet.accounts.application.search_by_user;

import com.roman.shared.domain.bus.query.QueryHandler;
import com.roman.wallet.accounts.application.AccountResponse;
import com.roman.wallet.accounts.domain.Account;
import com.roman.wallet.accounts.domain.AccountNotFoundError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchAccountByUserQueryHandler implements QueryHandler<SearchAccountByUserQuery, AccountResponse> {
    private final AccountFinderByUserId accountFinderByUserId;

    public SearchAccountByUserQueryHandler(AccountFinderByUserId accountFinderByUserId) {
        this.accountFinderByUserId = accountFinderByUserId;
    }

    @Override
    public AccountResponse handle(SearchAccountByUserQuery query) {
        Optional<Account> optional = accountFinderByUserId.find(query.userId());
        if (optional.isPresent()) {
            Account account = optional.get();
            return new AccountResponse(account.id().value(), account.userId().value(), account.balance().value());
        }
        throw new AccountNotFoundError(String.format("The account with userId <%s> not exists", query.userId()));
    }
}

package com.roman.shared.application.auth;

import com.roman.shared.domain.auth.AuthUser;
import com.roman.shared.domain.bus.query.QueryHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import org.springframework.stereotype.Service;

@Service
public class SearchTokenQueryHandler implements QueryHandler<SearchTokenQuery, UserAuthResponse> {
    private final TokenFinder finder;

    public SearchTokenQueryHandler(TokenFinder finder) {
        this.finder = finder;
    }

    @Override
    public UserAuthResponse handle(SearchTokenQuery query) throws QueryHandlerExecutionError {
        AuthUser user = finder.find(query.userId());
        return new UserAuthResponse(user.userId, user.username, user.token);
    }
}

package com.roman.user.auth.application.search_by_username;

import com.roman.shared.domain.bus.query.QueryHandler;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.user.auth.application.UserAuthResponse;
import com.roman.user.auth.domain.AuthUser;
import org.springframework.stereotype.Service;

@Service
public class SearchAuthUserByUsernameQueryHandler implements QueryHandler<SearchAuthUserByUsernameQuery, UserAuthResponse> {
    private final AuthUserFinderByUsername finder;

    public SearchAuthUserByUsernameQueryHandler(AuthUserFinderByUsername finder) {
        this.finder = finder;
    }

    @Override
    public UserAuthResponse handle(SearchAuthUserByUsernameQuery query) throws QueryHandlerExecutionError {
        AuthUser user = finder.find(query.username());
        return new UserAuthResponse(user.userId, user.username, user.token);
    }
}

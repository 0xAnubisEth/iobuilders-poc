package com.roman.user.auth.application.search_by_username;

import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.user.auth.domain.AuthUser;
import com.roman.user.auth.domain.AuthUserRepository;
import com.roman.user.users.application.search_by_username.SearchByUsernameQuery;
import com.roman.user.users.application.search_by_username.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthUserFinderByUsername {
    private final AuthUserRepository repository;
    private final QueryBus queryBus;

    public AuthUserFinderByUsername(AuthUserRepository repository, QueryBus queryBus) {
        this.repository = repository;
        this.queryBus = queryBus;
    }

    public AuthUser find(String username) throws QueryHandlerExecutionError {
        // Get user by username
        UserResponse user = queryBus.ask(new SearchByUsernameQuery(username));

        // Get auth user by userId
        return repository.findOneById(user.userId());
    }
}

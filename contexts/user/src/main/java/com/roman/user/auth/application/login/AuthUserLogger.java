package com.roman.user.auth.application.login;

import com.roman.shared.domain.TokenEncoder;
import com.roman.shared.domain.auth.AuthUser;
import com.roman.shared.domain.auth.AuthUserRepository;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.user.shared.domain.PasswordEncoder;
import com.roman.user.users.application.UserResponse;
import com.roman.user.users.application.search_by_username.SearchByUsernameQuery;
import org.springframework.stereotype.Service;

@Service
public class AuthUserLogger {
    private final AuthUserRepository repository;
    private final QueryBus queryBus;
    private final PasswordEncoder passwordEncoder;
    private final TokenEncoder tokenEncoder;

    public AuthUserLogger(AuthUserRepository repository, QueryBus queryBus, PasswordEncoder passwordEncoder, TokenEncoder tokenEncoder) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.passwordEncoder = passwordEncoder;
        this.tokenEncoder = tokenEncoder;
    }

    public void login(String username, String password) throws QueryHandlerExecutionError, UnauthorizedError {
        UserResponse userResponse;

        // Get user by username
        try {
            userResponse = queryBus.ask(new SearchByUsernameQuery(username));
        } catch (Exception e) {
            throw new UnauthorizedError();
        }

        // Match password
        if (!passwordEncoder.match(password, userResponse.password())) {
            throw new UnauthorizedError();
        }

        // Generate token
        String token = tokenEncoder.encode(userResponse.userId());

        // Save auth user
        repository.save(new AuthUser(userResponse.userId(), userResponse.username(), token));
    }
}

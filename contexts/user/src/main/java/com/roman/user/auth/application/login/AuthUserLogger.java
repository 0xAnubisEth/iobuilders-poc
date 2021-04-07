package com.roman.user.auth.application.login;

import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.user.auth.domain.AuthUser;
import com.roman.user.auth.domain.AuthUserRepository;
import com.roman.user.shared.domain.PasswordEncoder;
import com.roman.user.shared.domain.TokenEncoder;
import com.roman.user.users.application.search_by_username.SearchByUsernameQuery;
import com.roman.user.users.application.search_by_username.UserResponse;
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

    public void login(String username, String password) throws QueryHandlerExecutionError, UnauthorizedException {
        // Get user by username
        UserResponse userResponse = queryBus.ask(new SearchByUsernameQuery(username));

        // Match password
        if (!passwordEncoder.match(password, userResponse.password())) {
            throw new UnauthorizedException();
        }

        // Generate token
        String token = tokenEncoder.encode(userResponse.username());

        // Save auth user
        repository.save(new AuthUser(userResponse.userId(), userResponse.username(), token));
    }
}

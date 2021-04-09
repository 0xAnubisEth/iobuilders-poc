package com.roman.user.users.application.create;

import com.roman.shared.domain.bus.event.EventBus;
import com.roman.shared.domain.bus.query.QueryBus;
import com.roman.shared.domain.bus.query.QueryHandlerExecutionError;
import com.roman.user.shared.domain.PasswordEncoder;
import com.roman.user.users.application.UserResponse;
import com.roman.user.users.application.search_by_username.SearchByUsernameQuery;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserAlreadyExistsError;
import com.roman.user.users.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreator {
    private final UserRepository repository;
    private final EventBus eventBus;
    private final PasswordEncoder passwordEncoder;
    private final QueryBus queryBus;

    public UserCreator(UserRepository repository, EventBus eventBus, PasswordEncoder passwordEncoder, QueryBus queryBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.passwordEncoder = passwordEncoder;
        this.queryBus = queryBus;
    }

    void create(String id, String username, String password, String name, String lastname) throws QueryHandlerExecutionError, UserAlreadyExistsError {
        UserResponse userResponse = null;

        // Search user by username
        try {
            userResponse = queryBus.ask(new SearchByUsernameQuery(username));
        } catch (Exception ignored) {
        }

        if (userResponse != null) {
            throw new UserAlreadyExistsError(username);
        }

        // If not exists, create
        User user = User.create(id, username, passwordEncoder.encode(password), name, lastname);

        // Save in database
        repository.save(user);

        // Publish events
        eventBus.publish(user.pullDomainEvents());
    }
}

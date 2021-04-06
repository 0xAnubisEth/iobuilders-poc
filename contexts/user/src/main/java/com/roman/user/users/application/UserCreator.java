package com.roman.user.users.application;

import com.roman.shared.domain.bus.event.EventBus;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreator {
    private final UserRepository repository;
    private final EventBus eventBus;

    public UserCreator(UserRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    void create(String id, String username, String password, String name, String lastname) {
        User user = User.create(id, username, password, name, lastname);

        repository.save(user);

        eventBus.publish(user.pullDomainEvents());
    }
}

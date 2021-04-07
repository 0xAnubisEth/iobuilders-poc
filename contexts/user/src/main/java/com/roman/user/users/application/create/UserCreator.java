package com.roman.user.users.application.create;

import com.roman.user.shared.domain.PasswordEncoder;
import com.roman.shared.domain.bus.event.EventBus;
import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreator {
    private final UserRepository repository;
    private final EventBus eventBus;
    private final PasswordEncoder passwordEncoder;

    public UserCreator(UserRepository repository, EventBus eventBus, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.passwordEncoder = passwordEncoder;
    }

    void create(String id, String username, String password, String name, String lastname) {
        User user = User.create(id, username, passwordEncoder.encode(password), name, lastname);

        repository.save(user);

        eventBus.publish(user.pullDomainEvents());
    }
}

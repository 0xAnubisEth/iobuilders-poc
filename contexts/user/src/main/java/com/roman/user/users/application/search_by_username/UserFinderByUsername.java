package com.roman.user.users.application.search_by_username;

import com.roman.user.users.domain.User;
import com.roman.user.users.domain.UserNotFoundException;
import com.roman.user.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFinderByUsername {
    private final UserRepository repository;

    public UserFinderByUsername(UserRepository repository) {
        this.repository = repository;
    }

    public User find(String username) {
        Optional<User> optional = repository.findOneByUsername(username);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new UserNotFoundException(username);
    }

}

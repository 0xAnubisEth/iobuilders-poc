package com.roman.user.users.domain;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findOneByUsername(String username);
}

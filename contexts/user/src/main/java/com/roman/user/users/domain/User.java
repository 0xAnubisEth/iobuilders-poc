package com.roman.user.users.domain;

import com.roman.shared.domain.AggregateRoot;

public final class User extends AggregateRoot {
    private final UserId id;
    private final UserUsername username;
    private final UserPassword password;

    public User(UserId id, UserUsername username, UserPassword password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static User create(String id, String username, String password) {
        UserId userId = new UserId(id);
        UserUsername userUsername = new UserUsername(username);
        UserPassword userPassword = new UserPassword(password);
        User user = new User(userId, userUsername, userPassword);

        UserCreated userCreated = new UserCreated(userId.value(), userUsername.value(), userPassword.value());

        user.record(userCreated);

        return user;
    }

    public UserId id() {
        return id;
    }

    public UserUsername username() {
        return username;
    }

    public UserPassword password() {
        return password;
    }
}

package com.roman.user.users.domain;

import com.roman.shared.domain.AggregateRoot;

public final class User extends AggregateRoot {
    private final UserId id;
    private final UserUsername username;
    private final UserPassword password;
    private final UserName userName;
    private final UserLastName lastName;

    public User(UserId id, UserUsername username, UserPassword password, UserName userName, UserLastName lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userName = userName;
        this.lastName = lastName;
    }

    public static User create(String id, String username, String password, String name, String lastname) {
        UserId userId = new UserId(id);
        UserUsername userUsername = new UserUsername(username);
        UserPassword userPassword = new UserPassword(password);
        UserName userName = new UserName(name);
        UserLastName lastName = new UserLastName(lastname);
        User user = new User(userId, userUsername, userPassword, userName, lastName);

        UserCreated userCreated = new UserCreated(userId.value(), userUsername.value(), userPassword.value(), userName.value(), lastName.value());

        user.record(userCreated);

        return user;
    }

    public UserName userName() {
        return userName;
    }

    public UserLastName lastName() {
        return lastName;
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

package com.roman.user.users.domain;

public final class UserMother {
    public static User create(UserId id, UserUsername username, UserPassword password, UserName name, UserLastName lastName) {
        return new User(id, username, password, name, lastName);
    }

    public static User random() {
        return create(UserIdMother.random(), UserUsernameMother.random(), UserPasswordMother.random(), UserNameMother.random(), UserLastNameMother.random());
    }
}

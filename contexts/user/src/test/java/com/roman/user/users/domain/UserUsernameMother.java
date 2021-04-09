package com.roman.user.users.domain;

import com.roman.shared.domain.MotherCreator;

public class UserUsernameMother {
    public static UserUsername random() {
        return new UserUsername(MotherCreator.random().name().username());
    }
}

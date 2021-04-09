package com.roman.user.users.domain;

import com.roman.shared.domain.MotherCreator;

public class UserNameMother {
    public static UserName random() {
        return new UserName(MotherCreator.random().name().name());
    }
}

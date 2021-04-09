package com.roman.user.users.domain;

import com.roman.shared.domain.MotherCreator;

public class UserLastNameMother {
    public static UserLastName random() {
        return new UserLastName(MotherCreator.random().name().lastName());
    }
}

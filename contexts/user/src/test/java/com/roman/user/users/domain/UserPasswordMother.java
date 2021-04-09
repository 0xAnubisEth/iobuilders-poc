package com.roman.user.users.domain;

import com.roman.shared.domain.MotherCreator;

public class UserPasswordMother {
    public static UserPassword random() {
        return new UserPassword(MotherCreator.random().crypto().md5());
    }
}

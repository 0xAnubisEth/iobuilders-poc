package com.roman.user.users.domain;

import com.roman.shared.domain.UuidMother;

public class UserIdMother {
    public static UserId random() {
        return new UserId(UuidMother.random());
    }
}

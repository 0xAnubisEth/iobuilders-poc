package com.roman.user.users.domain;

import com.roman.shared.domain.StringValueObject;

public class UserUsername extends StringValueObject {
    public UserUsername(String value) {
        super(value);
    }

    public UserUsername() {
        super("");
    }
}

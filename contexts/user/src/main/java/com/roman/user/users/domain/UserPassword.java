package com.roman.user.users.domain;

import com.roman.shared.domain.StringValueObject;

public class UserPassword extends StringValueObject {
    public UserPassword(String value) {
        super(value);
    }

    public UserPassword() {
        super("");
    }
}

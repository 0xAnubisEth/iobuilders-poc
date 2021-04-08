package com.roman.user.users.domain;

import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.StringValueObject;

public class UserUsername extends StringValueObject {
    public UserUsername(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public UserUsername() {
        super("");
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Username is required");
        }
    }
}

package com.roman.user.users.domain;

import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.StringValueObject;

public class UserLastName extends StringValueObject {
    public UserLastName(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public UserLastName() {
        super("");
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Lastname is required");
        }
    }
}

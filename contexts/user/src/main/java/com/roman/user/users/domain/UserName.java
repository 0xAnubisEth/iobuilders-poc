package com.roman.user.users.domain;

import com.roman.shared.domain.InvalidArgumentError;
import com.roman.shared.domain.StringValueObject;

public class UserName extends StringValueObject {
    public UserName(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public UserName() {
        super("");
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Name is required");
        }
    }
}

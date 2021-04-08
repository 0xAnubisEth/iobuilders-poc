package com.roman.user.users.domain;

import com.roman.shared.domain.Identifier;
import com.roman.shared.domain.InvalidArgumentError;

public final class UserId extends Identifier {
    public UserId(String value) {
        super(value);
        ensureRequiredArgument(value);
    }

    public UserId() {
    }

    private void ensureRequiredArgument(String value) {
        if (value == null) {
            throw new InvalidArgumentError("Id is required");
        }
    }
}

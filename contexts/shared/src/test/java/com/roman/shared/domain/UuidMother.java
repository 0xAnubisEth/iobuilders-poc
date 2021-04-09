package com.roman.shared.domain;

import java.util.UUID;

public class UuidMother {
    public static String random() {
        return UUID.randomUUID().toString();
    }
}

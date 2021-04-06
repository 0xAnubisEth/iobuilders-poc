package com.roman.shared.domain;

import java.util.Objects;

public class FloatValueObject {
    private final Float value;

    public FloatValueObject(Float value) {
        this.value = value;
    }

    public Float value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FloatValueObject)) {
            return false;
        }
        FloatValueObject that = (FloatValueObject) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

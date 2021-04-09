package com.roman.shared.domain;

public class FloatMother {
    public static Float random() {
        return Float.parseFloat(MotherCreator.random().number().digit());
    }
}

package com.roman.user.shared.domain;

public interface PasswordEncoder {
    String encode(String text);
    Boolean match(String text, String hash);
}

package com.roman.user.shared.domain;

public interface TokenEncoder {
    String encode(String username);
}

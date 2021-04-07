package com.roman.user.shared.infrastructure;

import com.roman.user.shared.domain.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptEncoder implements PasswordEncoder {
    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String text) {
        return passwordEncoder.encode(text);
    }

    @Override
    public Boolean match(String text, String hash) {
        return passwordEncoder.matches(text, hash);
    }
}

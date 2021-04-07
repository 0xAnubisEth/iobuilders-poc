package com.roman.shared.application.auth;

import com.roman.shared.domain.auth.AuthUser;
import com.roman.shared.domain.auth.AuthUserRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenFinder {
    private final AuthUserRepository repository;

    public TokenFinder(AuthUserRepository repository) {
        this.repository = repository;
    }

    public AuthUser find(String userId) {
        return repository.findOneById(userId);
    }
}

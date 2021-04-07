package com.roman.shared.domain.auth;

public interface AuthUserRepository {
    void save(AuthUser user);
    AuthUser findOneById(String id);
}

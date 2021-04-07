package com.roman.user.auth.domain;

public interface AuthUserRepository {
    void save(AuthUser user);
    AuthUser findOneByToken(String token);
    AuthUser findOneByUsername(String username);
}

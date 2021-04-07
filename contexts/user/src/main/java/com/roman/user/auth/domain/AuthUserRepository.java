package com.roman.user.auth.domain;

public interface AuthUserRepository {
    void save(AuthUser user);
    AuthUser findOneById(String id);
}

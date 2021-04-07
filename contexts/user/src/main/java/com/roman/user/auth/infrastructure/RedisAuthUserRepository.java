package com.roman.user.auth.infrastructure;

import com.roman.user.auth.domain.AuthUser;
import com.roman.user.auth.domain.AuthUserRepository;
import org.springframework.stereotype.Service;

@Service
public class RedisAuthUserRepository implements AuthUserRepository {
    @Override
    public void save(AuthUser user) {

    }

    @Override
    public AuthUser findOneByToken(String token) {
        return null;
    }

    @Override
    public AuthUser findOneByUsername(String username) {
        return null;
    }
}

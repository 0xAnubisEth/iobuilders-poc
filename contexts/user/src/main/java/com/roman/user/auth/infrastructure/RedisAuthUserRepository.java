package com.roman.user.auth.infrastructure;

import com.roman.shared.infrastructure.redis.RedisRepository;
import com.roman.user.auth.domain.AuthUser;
import com.roman.user.auth.domain.AuthUserRepository;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisAuthUserRepository extends RedisRepository<AuthUser> implements AuthUserRepository {

    public RedisAuthUserRepository(RedisConnectionFactory factory) {
        super(factory);
    }

    @Override
    public void save(AuthUser user) {
        save(user.token, user);
    }

    @Override
    public AuthUser findOneByToken(String token) {
        return findById(token);
    }

    @Override
    public AuthUser findOneByUsername(String username) {
        return null;
    }
}

package com.roman.user.auth.infrastructure;

import com.roman.shared.infrastructure.redis.RedisRepository;
import com.roman.user.auth.domain.AuthUser;
import com.roman.user.auth.domain.AuthUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisAuthUserRepository extends RedisRepository<AuthUser> implements AuthUserRepository {

    public RedisAuthUserRepository(@Qualifier("redis_connection") RedisConnectionFactory factory) {
        super(factory);
    }

    @Override
    public void save(AuthUser user) {
        save(user.userId, user);
    }

    @Override
    public AuthUser findOneById(String id) {
        return findById(id);
    }
}

package com.roman.shared.infrastructure.auth;

import com.roman.shared.domain.auth.AuthUser;
import com.roman.shared.domain.auth.AuthUserRepository;
import com.roman.shared.infrastructure.redis.RedisRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class RedisAuthUserRepository extends RedisRepository<AuthUser> implements AuthUserRepository {
    private final Integer ttl;

    public RedisAuthUserRepository(@Qualifier("redis_connection") RedisConnectionFactory factory, Environment env) {
        super(factory);
        this.ttl = Integer.valueOf(env.getRequiredProperty("redis.expiration.session.token"));
    }

    @Override
    public void save(AuthUser user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, ttl);
        save(user.userId, user, calendar.getTime());
    }

    @Override
    public AuthUser findOneById(String id) {
        return findById(id);
    }
}

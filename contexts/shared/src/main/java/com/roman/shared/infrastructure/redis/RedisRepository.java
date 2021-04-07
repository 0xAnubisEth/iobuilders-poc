package com.roman.shared.infrastructure.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public abstract class RedisRepository<T> {

    private final RedisConnectionFactory factory;

    public RedisRepository(RedisConnectionFactory factory) {
        this.factory = factory;
    }

    protected RedisTemplate<String, T> redisTemplate() {
        final RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(factory);
        return template;
    }

    public void save(String id, T impl) {
        redisTemplate().opsForValue().set(id, impl);
    }

    public T findById(String id) {
        return redisTemplate().opsForValue().get(id);
    }

}

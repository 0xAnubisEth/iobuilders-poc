package com.roman.shared.infrastructure.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class RedisConfigurationFactory {
    private final Environment env;

    public RedisConfigurationFactory(Environment env) {
        this.env = env;
    }

    @Bean("redis_connection")
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(env.getRequiredProperty("redis.host"), Integer.parseInt(env.getRequiredProperty("redis.port"))));
    }

    @Bean("RCacheManager")
    public RedisCacheManager cacheManager(@Qualifier("redis_connection") RedisConnectionFactory factory) {
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(60L)))
                .build();
    }

}

package com.roman.shared.infrastructure.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfigurationFactory {
    private final Environment env;

    public RedisConfigurationFactory(Environment env) {
        this.env = env;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(env.getRequiredProperty("redis.host"), Integer.parseInt(env.getRequiredProperty("redis.port"))));
    }

}

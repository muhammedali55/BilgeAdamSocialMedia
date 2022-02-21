package com.bilgeadam.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching
@EnableRedisRepositories  // Redis üzerinden Entity Cach işlemi yapmak için bu annotation eklenmelidir.
public class RedisConfiguration {

    @Value("${auth_service_spring_rabbit_host}")
    private String server;
    /**
     *
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(server, 6379));
    }
}

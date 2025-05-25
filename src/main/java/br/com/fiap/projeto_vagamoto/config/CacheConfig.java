package br.com.fiap.projeto_vagamoto.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.annotation.JsonCreator;


@Configuration
public class CacheConfig {

    @Autowired
    private ObjectMapper springBootObjectMapper; 

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        ObjectMapper redisObjectMapper = new ObjectMapper();

        redisObjectMapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        redisObjectMapper.registerModule(new Jdk8Module());
        redisObjectMapper.registerModule(new JavaTimeModule());

        redisObjectMapper.activateDefaultTyping(
            BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class)
                .build(),
            ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.PROPERTY
        );

        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer =
            new GenericJackson2JsonRedisSerializer(redisObjectMapper);

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        config = config.disableCachingNullValues();

        return config;
    }
}
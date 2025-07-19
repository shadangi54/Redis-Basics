package com.product.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.product.dto.ProductDTO;

@Configuration
public class RedisConfig {

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration cacheConfigurationById = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new Jackson2JsonRedisSerializer<>(ProductDTO.class)));

		RedisCacheConfiguration cacheConfigurationAll = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class)));

		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		cacheConfigurations.put("PRODUCTS_CACHE", cacheConfigurationById);
		cacheConfigurations.put("ALL_PRODUCTS_CACHE", cacheConfigurationAll);

		return RedisCacheManager.builder(redisConnectionFactory).withInitialCacheConfigurations(cacheConfigurations)
				.cacheDefaults(cacheConfigurationById).build();
	}
}

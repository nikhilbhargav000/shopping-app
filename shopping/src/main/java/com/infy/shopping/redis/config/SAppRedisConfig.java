package com.infy.shopping.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class SAppRedisConfig {

	@Value("${sapp.redis.host}")
	public String REDIS_CLUSTER_HOST ;

	@Value("${sapp.redis.port}")
	public int REDIS_CLUSTER_PORT;
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setPort(REDIS_CLUSTER_PORT);
		jedisConnectionFactory.setHostName(REDIS_CLUSTER_HOST);
		return jedisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisRestTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		redisTemplate.setKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}
}

package com.example.demo.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig implements BeanClassLoaderAware{
	private ClassLoader loader;
		
	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		System.out.println("set redis serializer.");
		return new GenericJackson2JsonRedisSerializer(objectMapper());
	}

	/**
	 * Customized {@link ObjectMapper} to add mix-in for class that doesn't have default
	 * constructors
	 *
	 * @return the {@link ObjectMapper} to use
	 */
	
	@Bean
	public ObjectMapper objectMapper() {
		System.out.println("this.loader : " + this.loader);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
		return mapper;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader(java.lang
	 * .ClassLoader)
	 */
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.loader = classLoader;
	}
}

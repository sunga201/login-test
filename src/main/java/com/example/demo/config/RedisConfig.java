package com.example.demo.config;

import java.io.File;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
	
	@Bean
	public void test() throws IOException {
		Config config = new Config();
		config = Config.fromJSON(new File("C:\\spring-tool-suite-4-4.10.0.RELEASE-e4.19.0-win32.win32.x86_64.self-extracting\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\Servers\\Tomcat v9.0 Server at localhost-config\\redisson.conf"));
		System.out.println("config : " + config);
		System.out.println("redis config start, codec : " + config.getCodec());
		config.setCodec(new CustomJsonJacksonCodec());
		System.out.println("redis config end, codec : " + config.getCodec());
		RedissonClient redisson = Redisson.create(config);
	}
}

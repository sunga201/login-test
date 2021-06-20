package com.example.demo.config;

import org.redisson.codec.JsonJacksonCodec;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJsonJacksonCodec extends JsonJacksonCodec{

	@Override
	protected void init(ObjectMapper objectMapper) {
		System.out.println("CustomJsonJacksonCodec init.");
		super.init(objectMapper);
	    objectMapper.registerModule(new CoreJackson2Module());
	}
}

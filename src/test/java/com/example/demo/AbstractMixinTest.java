package com.example.demo;

import org.junit.Before;
import org.springframework.security.jackson2.SecurityJackson2Modules;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractMixinTest {

	protected ObjectMapper mapper;

	@Before
	public void setup() {
		this.mapper = new ObjectMapper();
		ClassLoader loader = getClass().getClassLoader();
		System.out.println("loader : " + loader);
		this.mapper.registerModules(SecurityJackson2Modules.getModules(loader));
	}

}
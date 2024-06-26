package com.onlineshop.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UtilityBeanConfig {
	
	
	
	
	@Bean
	Random randomObject()
	{
		return new SecureRandom();
	}
	
	

}

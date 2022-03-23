package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.spring.Client;
import com.radiantk.spring.User;

@Configuration
public class AppContext {
	
	@Bean
	public Client client() {
		Client client = new Client();
		client.setUser("host");
		
		return client;
	}
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public User user() {
		User user = new User();
		user.setUser("user");
		
		return user;
	}
}

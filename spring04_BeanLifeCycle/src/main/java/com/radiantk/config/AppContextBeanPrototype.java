package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.radiantk.spring.Client;
import com.radiantk.spring.User;

@Configuration
public class AppContextBeanPrototype {

	@Bean
	@Scope("prototype")
	public Client client() {
		Client client = new Client();
		client.setUser("host");
		
		return client;
	}
	
	@Bean
	@Scope("singleton")
	public User user() {
		User user = new User();
		user.setUser("user");
		
		return user;
	}
}

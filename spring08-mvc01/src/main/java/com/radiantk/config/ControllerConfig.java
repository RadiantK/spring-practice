package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.controller.RegisterController;

@Configuration
public class ControllerConfig {
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController();
	}
}

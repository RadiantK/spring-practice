package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.controller.HelloController;

@Configuration
public class ControllerConfig {
	
	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}

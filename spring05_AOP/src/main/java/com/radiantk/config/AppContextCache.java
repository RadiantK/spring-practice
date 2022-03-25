package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.radiantk.aspect.CacheAspect;
import com.radiantk.aspect.ExecuteTimeAspect;
import com.radiantk.test.Calculator;
import com.radiantk.test.RecursiveCalculator;

@Configuration
@EnableAspectJAutoProxy
public class AppContextCache {

	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	
	@Bean
	public ExecuteTimeAspect executeTimeAspect() {
		return new ExecuteTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecursiveCalculator();
	}
}

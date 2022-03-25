package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.radiantk.aspect.ExecuteTimeAspect;
import com.radiantk.test.Calculator;
import com.radiantk.test.RecursiveCalculator;
//인터페이스가 아닌 자바 클래스를 상속받아서 프록시를 생성할 때
// @EnableAspectJAutoProxy(proxyTargetClass = true) 
@Configuration
@EnableAspectJAutoProxy // 빈 객체가 인터페이스를 상속하면 인터페이스를 사용해서 프록시 생성
public class AppContext {

	@Bean
	public ExecuteTimeAspect executeTimeAspect() {
		return new ExecuteTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecursiveCalculator();
	}
}

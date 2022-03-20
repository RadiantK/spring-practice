package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.spring.MemberDao;
import com.radiantk.spring.MemberPrinter;

@Configuration
public class AppConfig1 {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}

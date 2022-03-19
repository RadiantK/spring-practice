package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.MemberDao;
import com.radiantk.spring.MemberRegisterService;

@Configuration
public class AppContext {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService pwdService = new ChangePasswordService();
		pwdService.setMemberDao(memberDao());
		
		return pwdService;
	}
}

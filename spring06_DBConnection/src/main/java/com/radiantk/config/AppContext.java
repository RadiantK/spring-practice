package com.radiantk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.MemberDao;
import com.radiantk.spring.MemberInfoPrinter;
import com.radiantk.spring.MemberListPrinter;
import com.radiantk.spring.MemberPrinter;
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
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter printer = new MemberInfoPrinter();
		printer.setMemberDao(memberDao());
		printer.setMemberPrinter(memberPrinter());
		
		return printer;
	}
	
}

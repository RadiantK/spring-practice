package com.radiantk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.controller.ChangePasswordController;
import com.radiantk.controller.LoginController;
import com.radiantk.controller.LogoutController;
import com.radiantk.controller.MemberDetailController;
import com.radiantk.controller.MemberListController;
import com.radiantk.controller.RegisterController;
import com.radiantk.controller.RestMemberController;
import com.radiantk.dao.MemberDao;
import com.radiantk.service.AuthService;
import com.radiantk.service.ChangePasswordService;
import com.radiantk.service.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	public MemberRegisterService memberRegisterService;
	@Autowired
	private AuthService authService;
	@Autowired
	private ChangePasswordService changePasswordService;
	@Autowired
	private MemberDao memberDao;
	
	@Bean
	public RegisterController registerController() {
		RegisterController registerController = new RegisterController();
		registerController.setMemberRegisterService(memberRegisterService);
		return registerController;
	}
	
	@Bean
	public LoginController loginController() {
		LoginController controller = new LoginController();
		controller.setAuthService(authService);
		return controller;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public ChangePasswordController changePasswordController() {
		ChangePasswordController controller = new ChangePasswordController();
		controller.setChangePasswordSerivce(changePasswordService);
		return controller;
	}
	
	@Bean
	public MemberListController memberListController() {
		MemberListController controller = new MemberListController();
		controller.setMemberListController(memberDao);
		return controller;
	}
	
	@Bean
	public MemberDetailController memberDetailController() {
		MemberDetailController controller = new MemberDetailController();
		controller.setMemberDao(memberDao);
		return controller;
	}
	
	@Bean
	public RestMemberController restMemberController() {
		RestMemberController controller = new RestMemberController();
		controller.setMemberDao(memberDao);
		controller.setMemberRegisterService(memberRegisterService);
		return controller;
	}
}

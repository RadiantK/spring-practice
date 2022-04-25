package com.radiantk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.controller.ChangePasswordController;
import com.radiantk.controller.LoginController;
import com.radiantk.controller.LogoutController;
import com.radiantk.controller.RegisterController;
import com.radiantk.spring.AuthService;
import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	public MemberRegisterService memberRegisterService;
	@Autowired
	private AuthService authService;
	@Autowired
	private ChangePasswordService changePasswordService;
	
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
}

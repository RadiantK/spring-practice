package com.radiantk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.radiantk.controller.RegisterController;
import com.radiantk.spring.MemberRegisterService;
import com.radiantk.survey.SurveyController;

@Configuration
public class ControllerConfig {
	
	@Autowired
	public MemberRegisterService memberRegisterService;
	
	@Bean
	public RegisterController registerController() {
		RegisterController registerController = new RegisterController();
		registerController.setMemberRegisterService(memberRegisterService);
		return registerController;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
}

package com.radiantk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.radiantk.spring.AuthInfo;
import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.WrongIdPasswordException;

@Controller
@RequestMapping("edit/changePassword")
public class ChangePasswordController {
	
	private ChangePasswordService changePasswordService;
	
	public void setChangePasswordSerivce(
			ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	@GetMapping
	public String form(
			@ModelAttribute("command") ChangePasswordCommand pwdCmd) {
	
		return "edit/changePwdForm";
	}
	
	@PostMapping
	public String submit(
			@ModelAttribute("command") ChangePasswordCommand pwdCmd,
			Errors errors, HttpSession session) {
		new ChangePasswordCommandValidator().validate(pwdCmd, errors);
		
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		try {
			changePasswordService.ChangePassword(
					authInfo.getEamil(),
					pwdCmd.getCurrentPassword(),
					pwdCmd.getNewPassword());
			return "edit/changePwd";
		}catch(WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}

package com.radiantk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantk.spring.DuplicateMemberException;
import com.radiantk.spring.MemberRegisterService;
import com.radiantk.spring.RegisterRequest;

@Controller
public class RegisterController {

	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(
			MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
		if(!agree) {
			return "register/step1";
		}
		return "register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	// 스프링은 요청 파라미터값을 커멘드 객체에 담아주는 기능을 한다.
	// 예를들어 이름이 name인 요청 파라미터 값을 커맨드 객체의 setName() 메소드를 사용해서
	// 커맨드 객체에 전달하는 기능을 제공한다.
	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq) {
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(DuplicateMemberException e) {
			return "register/step2";
		}
	}
}

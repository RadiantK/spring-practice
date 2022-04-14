package com.radiantk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if(!agree) {
			return "register/step1";
		}
		// <form:form>태그에서 사용할 커맨드 객체를 모델에 넣어줌
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	// 스프링은 요청 파라미터값을 커멘드 객체에 담아주는 기능을 한다.
	// 예를들어 이름이 name인 요청 파라미터 값을 커맨드 객체의 setName() 메소드를 사용해서
	// 커맨드 객체에 전달하는 기능을 제공한다.
	// 커맨드 객체는 보통 DTO 를 의미하며, HttpServletRequest 로 받아오는 요청 파라미터의
	// key 값과 동일한 이름의 속성들과 setter 메서드를 가지고 있어야 한다.
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

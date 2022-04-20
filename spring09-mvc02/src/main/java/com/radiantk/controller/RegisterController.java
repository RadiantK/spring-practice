package com.radiantk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
			// value : 요청 파라미터 이름 지정, required = 기본값 true
			// defaultValue : 요청 파라미터 값이 없을 때 사용할 문자열 값 지정. 기본값음 없음
			// 스프링 MVC는 파라미터 타입에 맞게 String 값을 변환해준다.
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
	// 커맨드 객체는 보통 DTO 를 의미하며, HttpServletRequest 로 받아오는 요청 파라미터의
	// key 값과 동일한 이름의 속성들과 setter 메서드를 가지고 있어야 한다.
	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq, Errors errors) {
		// Errors객체는 커맨드 객체의 특정 프로퍼티 값을 구할 수 있는 getFieldValue()를 제공
		new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors()) { // 에러가 존재하는지 검사
			return "register/step2";
		}
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(DuplicateMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
}

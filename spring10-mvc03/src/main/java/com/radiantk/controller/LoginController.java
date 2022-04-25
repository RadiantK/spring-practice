package com.radiantk.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.radiantk.spring.AuthInfo;
import com.radiantk.spring.AuthService;
import com.radiantk.spring.WrongIdPasswordException;

// 로그인 요청 처리
@Controller
@RequestMapping("/login")
public class LoginController {

	private AuthService authService;
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand, 
			@CookieValue(value = "REMEMBER", required=false) Cookie rCookie) {
		// value = 쿠키이름 , required - 쿠기가 존재하지 않을 수도 있음
		// 쿠키가 존재하면 값을 읽어와 커맨드 객체에 프로퍼티값을 설정
		if(rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(
			LoginCommand loginCommand, Errors errors, HttpSession session,
			HttpServletResponse response) {
		// 쿠키를 생성하는 부분은 로그인을 처리하는 submit() 이기 때문에 쿠키 생성을 위해서
		// HttpServletResponse객체를 사용한다
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(), loginCommand.getPassword());
			// TODO 세션에 authInfo를 저장해야 함
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = 
					new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30); // 1달 유지
			} else {
				rememberCookie.setMaxAge(0); // 쿠키 바로 제거
			}
			response.addCookie(rememberCookie); // 쿠키를 웹브라우저에게 보냄
			
			return "login/loginSuccess";
		}catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}

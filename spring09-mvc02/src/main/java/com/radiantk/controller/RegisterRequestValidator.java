package com.radiantk.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.radiantk.spring.RegisterRequest;

public class RegisterRequestValidator implements Validator {

	private static final String emailRegExp =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		// 주어진 정규표현식으로부터 패턴을 만들어낸다.(컴파일 한다고 표현) 
		pattern = Pattern.compile(emailRegExp);
	}
	
	// Validator가 검증할 수 있는 객체인지 검사
	@Override
	public boolean supports(Class<?> clazz) {
		// 파라미터로 전달받은 clazz객체가 RegisterRequest클래스로 타입 변환이 가능한지 검사
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	// target: 검사하는 대상 객체, errors: 검사 결과 에러 코드를 설정하기 위한 객체
	// validate(): 검사 대상 객체의 특정 프로퍼티나 상태가 올바른지 검사
	// 올바르지 않다면 Errors rejectValue() 메소드를 이용해서 에러 코드 저장
	@Override
	public void validate(Object target, Errors errors) {
		RegisterRequest regRequest = (RegisterRequest) target;
		if(regRequest.getEmail() == null || regRequest.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			// Matcher 객체는 특정한 문자열이 주어진 패턴과 일치하는가를 확인
			Matcher matcher = pattern.matcher(regRequest.getEmail());
			// 주어진 문자열 전체가 특정 패턴과 일치하는가를 판단
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regRequest.getPassword().isEmpty()) {
			if(!regRequest.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}

}

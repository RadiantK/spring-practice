package com.radiantk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.radiantk.command.ChangePasswordCommand;

public class ChangePasswordCommandValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, "currentPassword", "required");
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
	}
}

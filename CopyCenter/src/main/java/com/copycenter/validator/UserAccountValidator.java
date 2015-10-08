package com.copycenter.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.copycenter.pojo.UserAccount;

public class UserAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserAccount.class;
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		//UserAccount userAccount = (UserAccount) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.blank");

	}

}

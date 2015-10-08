package com.copycenter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.copycenter.DAO.UserAccountDAO;
import com.copycenter.pojo.StoreManager;
import com.copycenter.pojo.Student;

public class StoreManagerValidator implements Validator {
	@Autowired
	private UserAccountDAO userAccountDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == StoreManager.class;
	}

	@Override
	public void validate(Object object, Errors errors) {

		StoreManager storeManager = (StoreManager) object;

		boolean f = userAccountDAO.checkUserName(storeManager.getUserAccount()
				.getUserName());

		if (f != false) {
			errors.rejectValue("userAccount.userName", "username.used");
		}

		String contactNumber = storeManager.getContactNumber();

		try {
			if (contactNumber.length() > 10 || contactNumber.length() <= 9) {
				errors.rejectValue("contactNumber", "contactNumber.invalid");
			} else {
				int conNum = Integer.parseInt(contactNumber);

				if (conNum < 0) {
					errors.rejectValue("contactNumber", "contactNumber.special");
				}
			}

		} catch (Exception e) {
			errors.rejectValue("contactNumber", "contactNumber.invalid");

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"firstName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"lastName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNumber",
				"contactNumber.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"userAccount.userName", "userName.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"userAccount.password", "password.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"email.required");
	}
}

package com.softserveinc.edu.oms.web.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.edu.oms.web.user.model.UserModel;
import com.softserveinc.edu.oms.web.user.util.ValidatorRegex;

public class AddUserValidator implements Validator {

	private static final int MIN_LOGIN_LENGTH = 3;
	private static final int MAX_LOGIN_LENGTH = 13;
	private static final int MAX_NAME_LENGTH = 13;
	private static final int MIN_PASSWORD_LENGTH = 4;
	private static final int MAX_PASSWORD_LENGTH = 10;

	@Override
	public boolean supports(final Class<?> clazz) {
		return UserModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "regionID","uform.emptyField");
		ValidationUtils.rejectIfEmpty(errors, "roleID","uform.emptyField");

		UserModel userModel = (UserModel) target;
		
		if (userModel.getLogin() == null
				|| userModel.getLogin().trim().length() == 0) {
			errors.rejectValue("login", "uform.emptyField");
		} else if (!userModel.getLogin().matches(ValidatorRegex.LOGIN_REGEX)) {
			errors.rejectValue("login", "uform.lettersAndNumbersOnly");
		} else if (userModel.getLogin().length() < MIN_LOGIN_LENGTH
				|| userModel.getLogin().length() > MAX_LOGIN_LENGTH) {
			errors.rejectValue("login", "uform.loginIncorrectSize");
		}

		if (userModel.getFirstName() == null
				|| userModel.getFirstName().trim().length() == 0) {
			errors.rejectValue("firstName", "uform.emptyField");
		} else if (!userModel.getFirstName().matches(ValidatorRegex.NAME_REGEX)) {
			errors.rejectValue("firstName", "uform.lettersOnly");
		} else if (userModel.getFirstName().length() > MAX_NAME_LENGTH) {
			errors.rejectValue("firstName", "uform.tooLongName");
		}
		
		if (userModel.getLastName() == null
				|| userModel.getLastName().trim().length() == 0) {
			errors.rejectValue("lastName", "uform.emptyField");
		} else if (!userModel.getLastName().matches(ValidatorRegex.NAME_REGEX)) {
			errors.rejectValue("lastName", "uform.lettersOnly");
		} else if (userModel.getLastName().length() > MAX_NAME_LENGTH) {
			errors.rejectValue("lastName", "uform.tooLongName");
		}

		if (userModel.getPassword() == null
				|| userModel.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "uform.emptyField");
		} else if (userModel.getPassword().length() < MIN_PASSWORD_LENGTH
				|| userModel.getPassword().length() > MAX_PASSWORD_LENGTH) {
			errors.rejectValue("password", "uform.passwordIncorrectSize");
		} else if (userModel.getPassword().contains(" ")) {
			errors.rejectValue("password", "uform.passwordNoSpaces");
		}

		if (userModel.getConfirmPassword() == null
				|| userModel.getConfirmPassword().trim().length() == 0) {
			errors.rejectValue("confirmPassword", "uform.emptyField");
		} else if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "uform.passwordsNotMatch");
		}

		if (userModel.getEmail() == null
				|| userModel.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "uform.emptyField");
		} else if (!userModel.getEmail().matches(ValidatorRegex.EMAIL_REGEX)) {
			errors.rejectValue("email", "uform.invalidEmail");
		}
	}
}

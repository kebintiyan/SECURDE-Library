package com.securde.model.account;

import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by kevin on 6/27/2017.
 */
public class UserValidator implements Validator {

    private static final String ERROR_CODE_REQUIRED = "field.required";
    private static final String ERROR_LENGTH = "field.length";
    private static final String ERROR_CODE_MIN_LENGTH = "field.min.length";
    private static final String ERROR_CODE_MAX_LENGTH = "field.max.length";

    private static final int USERNAME_MIN_LENGTH = 16;
    private static final int USERNAME_MAX_LENGTH = 6;

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 16;

    private static final int ID_NUMBER_LENGTH = 8;

    private static final int EMAIL_MAX_LENGTH = 64;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        validateUsername(user.getUsername(), errors);
        validatePassword(user.getPassword(), errors);

        if (user.isRegularUser()) {
            validateIdNumber(user.getIdNumber(), errors);
            validateEmail(user.getEmail(), errors);
            validateFirstName(user.getFirstName(), errors);
            validateMiddleInitial(user.getMiddleInitial(), errors);
            validateLastName(user.getLastName(), errors);
            validateSecretQuestion(user.getSecretQuestion(), errors);
            validateSecretAnswer(user.getSecretAnswer(), errors);
        }
    }

    private void validateUsername(String username, Errors errors) {
        if (isNullOrEmpty(username)) {
            rejectRequired(errors, "username");
        }
        else if (username.length() < USERNAME_MAX_LENGTH) {
            errors.rejectValue("username", ERROR_CODE_MIN_LENGTH, "Username must be at least " +
                    USERNAME_MAX_LENGTH + " characters.");
        }
        else if (username.length() > USERNAME_MIN_LENGTH) {
            errors.rejectValue("username", ERROR_CODE_MAX_LENGTH, "Username cannot exceed " +
                    USERNAME_MIN_LENGTH + " characters.");
        }
    }

    private void validatePassword(String password, Errors errors) {
        if (isNullOrEmpty(password)) {
            rejectRequired(errors, "password");
        }
        else if (password.length() < PASSWORD_MIN_LENGTH) {
            errors.rejectValue("password", ERROR_CODE_MIN_LENGTH, "Password must be at least " +
                    PASSWORD_MIN_LENGTH + " characters.");
        }
        else if (password.length() > PASSWORD_MAX_LENGTH) {
            errors.rejectValue("username", ERROR_CODE_MAX_LENGTH, "Password cannot exceed " +
                    PASSWORD_MAX_LENGTH + " characters.");
        }
    }

    private void validateIdNumber(String idNumber, Errors errors) {
        if (isNullOrEmpty(idNumber)) {
            rejectRequired(errors, "ID number");
        }
        else if (idNumber.length() != ID_NUMBER_LENGTH) {
            errors.rejectValue("idNumber", ERROR_LENGTH, "ID Number must be exactly " + ID_NUMBER_LENGTH + " characters.");
        }
    }

    private void validateEmail(String email, Errors errors) {
        if (isNullOrEmpty(email)) {
            rejectRequired(errors, "email");
        }

        else if (email.length() > EMAIL_MAX_LENGTH) {
            errors.rejectValue("email", ERROR_CODE_MAX_LENGTH, "Your email address is too long!");
        }
    }

    private void validateFirstName(String firstName, Errors errors) {

    }

    private void validateMiddleInitial(String middleInitial, Errors errors) {

    }

    private void validateLastName(String lastName, Errors errors) {

    }

    private void validateSecretQuestion(String secretQuestion, Errors errors) {

    }

    private void validateSecretAnswer(String secretAnswer, Errors errors) {

    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    private void rejectRequired(Errors errors, String field) {
        errors.rejectValue(field, ERROR_CODE_REQUIRED, "Please input a valid " + field + ".");
    }
}

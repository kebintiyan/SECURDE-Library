package com.securde.validator;

public class PasswordValidator {

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 32;

    public boolean isValidPassword(String password) {
        if (isNullOrEmpty(password)) {
            return false;
        }
        else if (password.length() < PASSWORD_MIN_LENGTH) {
            return false;
        }
        else if (password.length() > PASSWORD_MAX_LENGTH) {
            return false;
        }

        return true;
    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

}

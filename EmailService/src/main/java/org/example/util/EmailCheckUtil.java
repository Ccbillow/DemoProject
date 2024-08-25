package org.example.util;

import org.apache.commons.lang3.StringUtils;

public class EmailCheckUtil {

    // email check regex
    private static final String EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     * email check method
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }

        return email.matches(EMAIL_REGEX);
    }

}

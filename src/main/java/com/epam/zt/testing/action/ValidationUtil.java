package com.epam.zt.testing.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUtil {

    public static boolean validate(String patternValue, String value) {
        Pattern pattern = Pattern.compile(patternValue);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}

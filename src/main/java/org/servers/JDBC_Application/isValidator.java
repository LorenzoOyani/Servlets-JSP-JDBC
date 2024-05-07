package org.servers.JDBC_Application;

import java.util.regex.Pattern;

public class isValidator {
    private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("^[a-zA-Z]+(?:[A-Z][a-z]*)*$");

    public static boolean isValidate(String username) {
        return CAMEL_CASE_PATTERN.pattern().matches(username);
    }

}

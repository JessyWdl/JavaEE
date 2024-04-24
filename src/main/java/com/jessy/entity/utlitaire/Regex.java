package com.jessy.entity.utlitaire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe utilitaire permet la validation d'une adresse e-mail en utilisant un pattern
 */
public class Regex {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String Email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(Email);
        return matcher.matches();
    }
}

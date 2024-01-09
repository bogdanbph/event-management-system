package org.example.eventsmanagementsystem.converter.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public final class PasswordEncoder {

    public static String encodeDecodePassword(String pass, boolean isEncoding) {
        char[] charArray = pass.toCharArray();
        StringBuilder result = new StringBuilder();

        for (char c : charArray) {
            if (isEncoding) {
                result.append((char)(c + pass.length()));
            } else {
                result.append((char)(c - pass.length()));
            }
        }

        return result.toString();
    }
}

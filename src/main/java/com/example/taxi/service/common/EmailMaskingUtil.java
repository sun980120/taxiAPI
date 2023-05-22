package com.example.taxi.service.common;

import java.util.Arrays;

public class EmailMaskingUtil {
    public static String maskUserEmail(String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            return userEmail;
        }
        int atIndex = userEmail.indexOf('@');
        int startIndex = atIndex - 2;
        char[] maskedChars = new char[startIndex-2];
        Arrays.fill(maskedChars, '*');
        String maskedUserEmail = userEmail.substring(0, 1) + new String(maskedChars) + userEmail.substring(startIndex);
        return maskedUserEmail;
    }
}

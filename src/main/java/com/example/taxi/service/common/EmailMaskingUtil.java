package com.example.taxi.service.common;

import java.util.Arrays;

public class EmailMaskingUtil {
    private static final int MASKED_CHAR_COUNT = 4;

    public static String maskUserEmail(String userEmail) {
        System.out.println(userEmail);
        if (userEmail == null | userEmail.isEmpty()) {
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

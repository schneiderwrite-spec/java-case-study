package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class IsinGenerator {

    public String generateIsin() {

        ThreadLocalRandom random = ThreadLocalRandom.current();
        String base = randomUpper(random) + randomUpper(random) + randomAlphaNumeric(random, 9);

        return base + computeCheckDigit(base);
    }

    public int computeCheckDigit(String elevenChars) {
        if (elevenChars == null || elevenChars.length() != 11) {
            throw new IllegalArgumentException("ISIN base must be exactly 11 characters");
        }

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < elevenChars.length(); i++) {
            char ch = elevenChars.charAt(i);
            if (ch >= '0' && ch <= '9') {
                digits.append(ch);
            } else if (ch >= 'A' && ch <= 'Z') {
                int v = 10 + (ch - 'A');
                digits.append(v);
            } else {
                throw new IllegalArgumentException("Invalid character in ISIN base: " + ch);
            }
        }

        int sum = 0;
        boolean doubleNext = true;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int digit = digits.charAt(i) - '0';
            if (doubleNext) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
            doubleNext = !doubleNext;
        }

        int mod = sum % 10;
        return (10 - mod) % 10;
    }

    private static char randomUpper(ThreadLocalRandom random) {
        return (char) ('A' + random.nextInt(26));
    }

    private static String randomAlphaNumeric(ThreadLocalRandom random, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int pick = random.nextInt(36);
            if (pick < 10) {
                sb.append((char) ('0' + pick));
            } else {
                sb.append((char) ('A' + (pick - 10)));
            }
        }
        return sb.toString();
    }
}

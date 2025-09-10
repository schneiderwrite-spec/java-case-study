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

        //TODO implement
        return 0;
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

package com.solvians.showcase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IsinGeneratorTest {

    IsinGenerator subject = new IsinGenerator();

    //generated ISINs from http://copocorp.free.fr/tools/isin/
    @ParameterizedTest(name = "computeCheckDigit correctly for base {0} = {1}")
    @CsvSource({"DE201651558, 2",
            "LI326767130, 2",
            "EH174857083, 8"})
    void computeCheckDigit_generatesCorrectDigit(String base, int expected) {
        assertEquals(11, base.length(), "base must be 11 chars");
        assertEquals(expected, subject.computeCheckDigit(base));
    }

    @Test
    void computeCheckDigit_rejectsNullWrongLengthAndBadChars() {
        assertThrows(IllegalArgumentException.class, () -> subject.computeCheckDigit(null));
        assertThrows(IllegalArgumentException.class, () -> subject.computeCheckDigit("TOO-SHORT"));
        assertThrows(IllegalArgumentException.class, () -> subject.computeCheckDigit("DE1234567890"));
        assertThrows(IllegalArgumentException.class, () -> subject.computeCheckDigit("D€123456789"));
        assertThrows(IllegalArgumentException.class, () -> subject.computeCheckDigit("De123456789"));
    }

    @Test
    void generateIsin_hasCorrectFormatAndValidCheckDigit() {

        for (int i = 0; i < 10; i++) {
            String isin = subject.generateIsin();
            assertNotNull(isin);
            assertEquals(12, isin.length(), "ISIN must be 12 chars");

            assertTrue(isUpper(isin.charAt(0)) && isUpper(isin.charAt(1)), "First two must be A-Z");

            for (int j = 2; j < 11; j++) {
                char c = isin.charAt(j);
                assertTrue(isDigit(c) || isUpper(c), "Middle 9 must be alphanumeric (A-Z/0-9)");
            }

            char last = isin.charAt(11);
            assertTrue(isDigit(last), "Last must be a digit");

            String base = isin.substring(0, 11);
            int expected = subject.computeCheckDigit(base);
            assertEquals(last - '0', expected, "Check digit must match");
        }
    }

    private static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
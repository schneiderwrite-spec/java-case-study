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
}
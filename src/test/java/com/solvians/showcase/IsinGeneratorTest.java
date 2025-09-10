package com.solvians.showcase;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsinGeneratorTest {

    IsinGenerator subject = new IsinGenerator();

    //generated ISINs from http://copocorp.free.fr/tools/isin/
    @ParameterizedTest(name = "computeCheckDigit correctly for base {0} = {1}")
    @CsvSource({"DE201651558, 2",
                "LI326767130, 2",
                "EH174857083, 8"})
    void computeCheckDigitCorrect(String base, int expected) {
        assertEquals(11, base.length(), "base must be 11 chars");
        assertEquals(expected, subject.computeCheckDigit(base));
    }
}
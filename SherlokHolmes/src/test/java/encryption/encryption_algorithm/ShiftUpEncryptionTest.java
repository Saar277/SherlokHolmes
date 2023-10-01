package encryption.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.ShiftUpEncryption;
import org.example.finals.Finals;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShiftUpEncryptionTest {
    private static ShiftUpEncryption shiftUpEncryption;

    @BeforeClass
    public static void beforeClass() {
        shiftUpEncryption = new ShiftUpEncryption();
    }

    @Test
    public void calculateForEncode_regular() {
        int actual = shiftUpEncryption.calculateForEncode(100, 50);
        final int EXPECTED = 150;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_regular() {
        int actual = shiftUpEncryption.calculateForDecode(100, 50);
        final int EXPECTED = 50;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withNegativeKey() {
        int actual = shiftUpEncryption.calculateForEncode(100, -20);
        final int EXPECTED = 80;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withZeroKey() {
        int actual = shiftUpEncryption.calculateForEncode(100, 0);
        final int EXPECTED = 100;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_withZeroKey() {
        int actual = shiftUpEncryption.calculateForDecode(100, 0);
        final int EXPECTED = 100;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_withNegativeKey() {
        int actual = shiftUpEncryption.calculateForDecode(100, -20);
        final int EXPECTED = 120;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withTwoNumbersSumBiggerThanCharMaxValue() {
        int actual = shiftUpEncryption.calculateForEncode(Finals.MAX_CHAR_VALUE, 20000);
        final int EXPECTED = 20000;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withTwoNumbersNegativeSum() {
        int actual = shiftUpEncryption.calculateForEncode(100, -500);
        final int EXPECTED = -400;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_withTwoNumbersNegativeSum() {
        int actual = shiftUpEncryption.calculateForDecode(100, 105);
        final int EXPECTED = 65530;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_whenResultIsNegativeNumberBiggerThanCharMaxValue() {
        int actual = shiftUpEncryption.calculateForEncode(-Finals.MAX_CHAR_VALUE, -60000);
        final int EXPECTED = (-Finals.MAX_CHAR_VALUE - 60000) % Finals.MAX_CHAR_VALUE;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_whenResultIsNegativeNumberBiggerThanCharMaxValue() {
        int actual = shiftUpEncryption.calculateForDecode(-Finals.MAX_CHAR_VALUE, 60000);
        final int EXPECTED = 5535;

        assertEquals(EXPECTED, actual);
    }
}

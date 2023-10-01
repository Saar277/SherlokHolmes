package encryption.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.ShiftMultiplyEncryption;
import org.example.finals.Finals;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShiftMultiplyEncryptionTest {
    private static ShiftMultiplyEncryption shiftMultiplyEncryption;

    @BeforeClass
    public static void beforeClass() {
        shiftMultiplyEncryption = new ShiftMultiplyEncryption();
    }

    @Test
    public void calculateForEncode_regular() {
        int actual = shiftMultiplyEncryption.calculateForEncode(100, 5);
        final int EXPECTED = 500;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_whenTwoNumbersMultiplicationBiggerThanCharMaxValue() {
        int actual = shiftMultiplyEncryption.calculateForEncode(Finals.MAX_CHAR_VALUE + 1000, 4);
        final int EXPECTED = 4000;

        assertEquals(EXPECTED, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateForEncode_whenTwoNumbersMultiplicationSmallerThanZero() {
        shiftMultiplyEncryption.calculateForEncode(100, -1);
    }

    @Test
    public void calculateForEncode_whenTwoNumbersAreNegative() {
        int actual = shiftMultiplyEncryption.calculateForEncode(-100, -1);
        final int EXPECTED = 100;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withZeroNumber() {
        int actual = shiftMultiplyEncryption.calculateForEncode(100, 0);
        final int EXPECTED = 0;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void decode_regular() {
        char encryptedLetter = 27087;
        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(4831);

        String actual = shiftMultiplyEncryption.decode(String.valueOf(encryptedLetter), keys);
        final String EXPECTED = "W";

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void encodeAndDecode_encodeLetterBiggerThanCharMaxValueBeforeModulo() {
        String letter = "W";
        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(7907);

        String encryptedLetter = shiftMultiplyEncryption.decode(letter, keys);

        String actual = shiftMultiplyEncryption.decode(encryptedLetter, keys);
        final String EXPECTED = letter;

        assertEquals(EXPECTED, actual);
    }
}

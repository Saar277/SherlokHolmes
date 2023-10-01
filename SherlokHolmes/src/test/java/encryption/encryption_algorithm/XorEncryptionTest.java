package encryption.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.XorEncryption;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class XorEncryptionTest {
    private static XorEncryption xorEncryption;

    @BeforeClass
    public static void beforeClass() {
        xorEncryption = new XorEncryption();
    }

    @Test
    public void calculateForEncode_regular() {
        int actual = xorEncryption.calculateForEncode(100, 105);
        final int EXPECTED = 13;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_whenOneNumberIsNegative() {
        int actual = xorEncryption.calculateForEncode(100, -105);
        final int EXPECTED = -13;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_whenTwoNumbersAreNegative() {
        int actual = xorEncryption.calculateForEncode(-100, -105);
        final int EXPECTED = 11;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withZeroNumber() {
        int actual = xorEncryption.calculateForEncode(0, 14);
        final int EXPECTED = 14;

        assertEquals(EXPECTED, actual);
    }
}

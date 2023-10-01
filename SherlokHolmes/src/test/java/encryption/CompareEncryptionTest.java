package encryption;

import org.example.encryption_algorithm.CompareEncryption;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_algorithm.algorithm.ShiftUpEncryption;
import org.example.exception.EmptyKeysArrayException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompareEncryptionTest {
    private static CompareEncryption compareEncryption;
    private Encryption firstEncryption;
    private Encryption secondEncryption;

    @BeforeClass
    public static void beforeClass() {
        compareEncryption = new CompareEncryption();
    }

    @Before
    public void before() {
        this.firstEncryption = new ShiftUpEncryption();
        this.secondEncryption = new ShiftUpEncryption();
    }

    @Test(expected = EmptyKeysArrayException.class)
    public void compare_encryptionSetKeyStrengthWithEmptyKeysArray() throws EmptyKeysArrayException {
        ArrayList<Integer> firstEncryptionKeys = new ArrayList<>();

        ArrayList<Integer> secondEncryptionKeys = new ArrayList<>();
        secondEncryptionKeys.add(1);

        compareEncryption.compare(this.firstEncryption, this.secondEncryption,
                firstEncryptionKeys, secondEncryptionKeys);
    }

    @Test
    public void compare_firstEncryptionHaveTheStrongestKey() throws EmptyKeysArrayException {
        ArrayList<Integer> firstEncryptionKeys = new ArrayList<>();
        firstEncryptionKeys.add(100);

        ArrayList<Integer> secondEncryptionKeys = new ArrayList<>();
        secondEncryptionKeys.add(1);

        Encryption actual = compareEncryption.compare(this.firstEncryption, this.secondEncryption,
                firstEncryptionKeys, secondEncryptionKeys);
        final Encryption EXPECTED = this.firstEncryption;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void compare_secondEncryptionHaveTheStrongestKey() throws EmptyKeysArrayException {
        ArrayList<Integer> firstEncryptionKeys = new ArrayList<>();
        firstEncryptionKeys.add(100);

        ArrayList<Integer> secondEncryptionKeys = new ArrayList<>();
        secondEncryptionKeys.add(-10000);

        Encryption actual = compareEncryption.compare(this.firstEncryption, this.secondEncryption,
                firstEncryptionKeys, secondEncryptionKeys);
        final Encryption EXPECTED = this.secondEncryption;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void compare_bothEncryptionHaveTheSameStrongestKey() throws EmptyKeysArrayException {
        ArrayList<Integer> firstEncryptionKeys = new ArrayList<>();
        firstEncryptionKeys.add(100);

        ArrayList<Integer> secondEncryptionKeys = new ArrayList<>();
        secondEncryptionKeys.add(100);

        Encryption actual = compareEncryption.compare(this.firstEncryption, this.secondEncryption,
                firstEncryptionKeys, secondEncryptionKeys);
        final Encryption EXPECTED = this.firstEncryption;

        assertEquals(EXPECTED, actual);
    }
}

package encryption.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.DoubleEncryption;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_algorithm.algorithm.RepeatEncryption;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DoubleEncryptionTest {
    @Mock
    private static Encryption encryption = mock(Encryption.class);
    private static DoubleEncryption doubleEncryption;
    private static ArrayList<Integer> keys;
    private static char letter;

    @BeforeClass
    public static void beforeClass() {
        doubleEncryption = new DoubleEncryption(encryption, encryption);
        letter = (char) 87;
    }

    @Before
    public void before() {
        keys = new ArrayList<>();
        keys.add(10);
        keys.add(20);

        Mockito.when(encryption.encode(String.valueOf(letter), keys)).thenReturn("a");
        Mockito.when(encryption.encode("a", keys)).thenReturn("u");

        Mockito.when(encryption.decode(String.valueOf(letter), keys)).thenReturn("M");
        Mockito.when(encryption.decode("M", keys)).thenReturn("9");
    }

    @Test
    public void encode_regular() {
        String actual = doubleEncryption.encode(String.valueOf(letter), keys);
        final String EXPECTED = String.valueOf((char) 117);

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void decode_regular() {
        String actual = doubleEncryption.decode(String.valueOf(letter), keys);
        final String EXPECTED = String.valueOf((char) 57);

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void encode_doubleOfDouble() {
        DoubleEncryption doubleEncryption = new DoubleEncryption(new DoubleEncryption(encryption, encryption),
                new DoubleEncryption(encryption, encryption));

        keys.add(30);
        keys.add(40);

        Mockito.when(encryption.encode(String.valueOf('u'), keys)).thenReturn("\u0093");
        Mockito.when(encryption.encode("\u0093", keys)).thenReturn("»");

        String actual = doubleEncryption.encode(String.valueOf(letter), keys);
        final String EXPECTED = String.valueOf((char) 187);

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void decode_doubleOfDouble() {
        DoubleEncryption doubleEncryption = new DoubleEncryption(new DoubleEncryption(encryption, encryption),
                new DoubleEncryption(encryption, encryption));

        keys.add(6);
        keys.add(4);

        Mockito.when(encryption.decode("9", keys)).thenReturn("3");
        Mockito.when(encryption.decode("3", keys)).thenReturn("/");

        String actual = doubleEncryption.decode(String.valueOf(letter), keys);
        final String EXPECTED = String.valueOf((char) 47);
        assertEquals(EXPECTED, actual);
    }

    @Test
    public void encode_repeat() {
        RepeatEncryption repeatEncryption = new RepeatEncryption(2, encryption);
        DoubleEncryption doubleEncryption = new DoubleEncryption(repeatEncryption, repeatEncryption);

        ArrayList<Integer> firstEncryptionKeys = new ArrayList<>();
        firstEncryptionKeys.add(keys.get(0));
        ArrayList<Integer> secondEncryptionKeys = new ArrayList<>();
        secondEncryptionKeys.add(keys.get(1));

        Mockito.when(encryption.keysAmount()).thenReturn(1);
        Mockito.when(encryption.encode(String.valueOf(letter), firstEncryptionKeys)).thenReturn("a");
        Mockito.when(encryption.encode("a", firstEncryptionKeys)).thenReturn("u");
        Mockito.when(encryption.encode("u", secondEncryptionKeys)).thenReturn("\u007F");
        Mockito.when(encryption.encode("\u007F", secondEncryptionKeys)).thenReturn("\u0093");

        String actual = doubleEncryption.encode(String.valueOf(letter), keys);
        final String EXPECTED = String.valueOf((char) 147);

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void decode_repeat() {
        RepeatEncryption repeatEncryption = new RepeatEncryption(2, encryption);
        DoubleEncryption doubleEncryption = new DoubleEncryption(repeatEncryption, repeatEncryption);

        ArrayList<Integer> firstEncryptionKeys = new ArrayList<>();
        firstEncryptionKeys.add(keys.get(0));
        ArrayList<Integer> secondEncryptionKeys = new ArrayList<>();
        secondEncryptionKeys.add(keys.get(1));

        Mockito.when(encryption.keysAmount()).thenReturn(1);
        Mockito.when(encryption.decode(String.valueOf(letter), firstEncryptionKeys)).thenReturn("M");
        Mockito.when(encryption.decode("M", firstEncryptionKeys)).thenReturn("C");
        Mockito.when(encryption.decode("C", secondEncryptionKeys)).thenReturn("/");
        Mockito.when(encryption.decode("/", secondEncryptionKeys)).thenReturn("\u001B");

        String actual = doubleEncryption.decode(String.valueOf(letter), keys);
        final String EXPECTED = String.valueOf((char) 27);

        assertEquals(EXPECTED, actual);
    }

}

package encryption.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EncryptionTest {
    @Mock
    private static Encryption encryption = mock(Encryption.class, CALLS_REAL_METHODS);
    private final static char aLetter = 'a';
    private final static char wLetter = 'w';
    private static ArrayList<Integer> keys;
    private static final int key = 10;
    private String text;

    @BeforeClass
    public static void beforeClass() {
        keys = new ArrayList<>();
        keys.add(key);
        doReturn(0).when(encryption).calculateForEncode(anyInt(), anyInt());
        doReturn(0).when(encryption).calculateForDecode(anyInt(), anyInt());

    }

    @Test
    public void encodeAndDecode_regular() {
        this.text = String.valueOf(aLetter) + wLetter;

        String encryptedText = encryption.encode(this.text, keys);

        int actual = encryption.decode(encryptedText, keys).length();
        final int EXPECTED = 2;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void encodeAndDecode_withEmptyString() {
        this.text = "";

        String encryptedText = encryption.encode(this.text, keys);

        int actual = encryption.decode(encryptedText, keys).length();
        final int EXPECTED = 0;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void encodeAndDecode_withSpecialCharacters() {
        this.text = "a//\\ \n / ?  ";

        String encryptedText = encryption.encode(this.text, keys);

        int actual = encryption.decode(encryptedText, keys).length();
        final int EXPECTED = 12;

        assertEquals(EXPECTED, actual);
    }
}

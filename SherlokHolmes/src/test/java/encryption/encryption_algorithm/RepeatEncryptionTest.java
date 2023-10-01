package encryption.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.DoubleEncryption;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_algorithm.algorithm.RepeatEncryption;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RepeatEncryptionTest {
    @Mock
    private Encryption encryption = mock(Encryption.class);
    private RepeatEncryption repeatEncryption;

    @Test
    public void calculateForEncode_regular() {
        this.repeatEncryption = new RepeatEncryption(2, encryption);

        Mockito.when(encryption.calculateForEncode(50, 10)).thenReturn(60);
        Mockito.when(encryption.calculateForEncode(60, 10)).thenReturn(70);

        int actual = repeatEncryption.calculateForEncode(50, 10);
        final int EXPECTED = 70;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_regular() {
        this.repeatEncryption = new RepeatEncryption(2, encryption);

        Mockito.when(encryption.calculateForDecode(50, 10)).thenReturn(40);
        Mockito.when(encryption.calculateForDecode(40, 10)).thenReturn(30);

        int actual = repeatEncryption.calculateForDecode(50, 10);
        final int EXPECTED = 30;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withOneRepetition() {
        Mockito.when(encryption.calculateForEncode(50, 10)).thenReturn(60);
        RepeatEncryption repeatEncryption = new RepeatEncryption(1, encryption);

        int actual = repeatEncryption.calculateForEncode(50, 10);
        final int EXPECTED = 60;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_withOneRepetition() {
        Mockito.when(encryption.calculateForDecode(50, 10)).thenReturn(40);
        RepeatEncryption repeatEncryption = new RepeatEncryption(1, encryption);

        int actual = repeatEncryption.calculateForDecode(50, 10);
        final int EXPECTED = 40;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_withZeroRepetition() {
        this.repeatEncryption = new RepeatEncryption(0, encryption);

        int actual = repeatEncryption.calculateForEncode(50, 10);
        final int EXPECTED = 50;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_withZeroRepetition() {
        this.repeatEncryption = new RepeatEncryption(0, encryption);

        int actual = repeatEncryption.calculateForDecode(50, 10);
        final int EXPECTED = 50;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForEncode_whenTwoNumbersSumBiggerThanCharMaxValue() {
        this.repeatEncryption = new RepeatEncryption(2, encryption);

        Mockito.when(encryption.calculateForEncode(60000, 10000)).thenReturn(4465);
        Mockito.when(encryption.calculateForEncode(4465, 10000)).thenReturn(14465);

        int actual = repeatEncryption.calculateForEncode(60000, 10000);
        final int EXPECTED = 14465;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void calculateForDecode_whenTwoNumbersHaveNegativeSum() {
        this.repeatEncryption = new RepeatEncryption(2, encryption);

        Mockito.when(encryption.calculateForDecode(10000, 20000)).thenReturn(-10000);
        Mockito.when(encryption.calculateForDecode(-10000, 20000)).thenReturn(-30000);

        int actual = repeatEncryption.calculateForDecode(10000, 20000);
        final int EXPECTED = -30000;

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void encode_withDouble() {
        DoubleEncryption doubleEncryption = new DoubleEncryption(encryption, encryption);
        this.repeatEncryption = new RepeatEncryption(2, doubleEncryption);

        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(10);
        keys.add(20);

        Mockito.when(encryption.keysAmount()).thenReturn(1);
        Mockito.when(encryption.encode("W", keys)).thenReturn("a");
        Mockito.when(encryption.encode("a", keys)).thenReturn("u");
        Mockito.when(encryption.encode("u", keys)).thenReturn("\u007F");
        Mockito.when(encryption.encode("\u007F", keys)).thenReturn("\u0093");

        String actual = repeatEncryption.encode("W", keys);
        final String EXPECTED = String.valueOf((char) 147);

        assertEquals(String.valueOf((char) 147), actual);
    }

    @Test
    public void decode_withDouble() {
        DoubleEncryption doubleEncryption = new DoubleEncryption(encryption, encryption);
        this.repeatEncryption = new RepeatEncryption(2, doubleEncryption);

        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(10);
        keys.add(20);

        Mockito.when(encryption.keysAmount()).thenReturn(1);
        Mockito.when(encryption.decode("W", keys)).thenReturn("M");
        Mockito.when(encryption.decode("M", keys)).thenReturn("9");
        Mockito.when(encryption.decode("9", keys)).thenReturn("/");
        Mockito.when(encryption.decode("/", keys)).thenReturn("\u001B");

        String actual = repeatEncryption.decode("W", keys);
        final String EXPECTED = String.valueOf((char) 27);

        assertEquals(String.valueOf((char) 27), actual);
    }


}

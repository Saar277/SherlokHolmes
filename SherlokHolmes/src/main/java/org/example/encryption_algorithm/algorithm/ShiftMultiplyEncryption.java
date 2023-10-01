package org.example.encryption_algorithm.algorithm;

import org.example.enums.EncryptionAlgorithmsNames;
import org.example.exception.InvalidEncryptionKeyException;
import org.example.finals.Finals;
import org.example.keys.KeyGenerator;

import java.util.ArrayList;

public class ShiftMultiplyEncryption extends Encryption {
    @Override
    public int calculateForEncode(int number, int key) {
        return Integer.parseUnsignedInt(String.valueOf((number * key) % Finals.MAX_CHAR_VALUE));
    }

    @Override
    public int calculateForDecode(int number, int key) {
        return Integer.parseUnsignedInt(String.valueOf((number * key) % Finals.MAX_CHAR_VALUE));
    }

    @Override
    public String decode(String text, ArrayList<Integer> keys) {
        keys.set(0, findInverseKey(keys.get(0)));

        return super.decode(text, keys);
    }

    private static int findInverseKey(int key) {
        int inverseKey = 3;

        while ((key * inverseKey) % (Finals.MAX_CHAR_VALUE) != 1) {
            inverseKey += 2;
        }

        return inverseKey;
    }

    @Override
    public void createKey(ArrayList<Integer> keys) {
        keys.add(KeyGenerator.createPrimeRandomNumber());
    }

    @Override
    public void checkIfKeysValid(ArrayList<Integer> keys) throws InvalidEncryptionKeyException {
        if (!KeyGenerator.checkIfPrimeRandomKeyValid(keys.get(0))) {
            throw new InvalidEncryptionKeyException(keys.get(0));
        }
    }

    @Override
    public String name() {
        return EncryptionAlgorithmsNames.SHIFTMULTIPLY.label;
    }
}

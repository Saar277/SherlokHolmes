package org.example.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.exception.EmptyKeysArrayException;
import org.example.userio.UserIOInstance;

import java.util.ArrayList;

public class CompareEncryption {
    public Encryption compare(Encryption first, Encryption second,
                              ArrayList<Integer> firstEncryptionKeys, ArrayList<Integer> secondEncryptionKeys)
            throws EmptyKeysArrayException {
        checkIfEncryptionKeys(firstEncryptionKeys);
        checkIfEncryptionKeys(secondEncryptionKeys);

        int firstEncryptionMaxLengthKeyLength = first.keyStrength(firstEncryptionKeys);
        int secondEncryptionMaxLengthKeyLength = second.keyStrength(secondEncryptionKeys);

        if (firstEncryptionMaxLengthKeyLength > secondEncryptionMaxLengthKeyLength) {
            UserIOInstance.getInstance().print("first encryption have the strongest key");
            return first;
        } else if (secondEncryptionMaxLengthKeyLength > firstEncryptionMaxLengthKeyLength) {
            UserIOInstance.getInstance().print("second encryption have the strongest key");
            return second;
        } else {
            UserIOInstance.getInstance().print("both encryption have the same key strength");
            return first;
        }
    }

    private void checkIfEncryptionKeys(ArrayList<Integer> keys) throws EmptyKeysArrayException {
        if (keys.size() == 0) {
            throw new EmptyKeysArrayException();
        }
    }
}

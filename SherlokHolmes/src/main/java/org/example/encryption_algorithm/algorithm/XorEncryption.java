package org.example.encryption_algorithm.algorithm;

import org.example.enums.EncryptionAlgorithmsNames;
import org.example.exception.InvalidEncryptionKeyException;
import org.example.keys.KeyGenerator;

import java.util.ArrayList;

public class XorEncryption extends Encryption {
    @Override
    public int calculateForEncode(int number, int key) {
        return number ^ key;
    }

    @Override
    public int calculateForDecode(int number, int key) {
        return number ^ key;
    }

    @Override
    public void createKey(ArrayList<Integer> keys) {
        keys.add(KeyGenerator.createRandomNumberKey());
    }

    @Override
    public void checkIfKeysValid(ArrayList<Integer> keys) throws InvalidEncryptionKeyException {
        if (!KeyGenerator.checkIfRandomNumberKeyValid(keys.get(0))) {
            throw new InvalidEncryptionKeyException(keys.get(0));
        }
    }

    @Override
    public String name() {
        return EncryptionAlgorithmsNames.XOR.label;
    }
}

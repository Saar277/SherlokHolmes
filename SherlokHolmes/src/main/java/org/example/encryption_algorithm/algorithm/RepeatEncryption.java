package org.example.encryption_algorithm.algorithm;

import org.example.enums.EncryptionAlgorithmsNames;
import org.example.exception.InvalidEncryptionKeyException;

import java.util.ArrayList;

public class RepeatEncryption extends Encryption {
    private int repetition;
    private Encryption encryption;

    public RepeatEncryption(int repetition, Encryption encryption) {
        this.repetition = repetition;
        this.encryption = encryption;
    }

    @Override
    public int calculateForEncode(int number, int key) {
        for (int index = 0; index < repetition; index++) {
            number = encryption.calculateForEncode(number, key);
        }

        return number;
    }

    @Override
    public int calculateForDecode(int number, int key) {
        for (int index = 0; index < repetition; index++) {
            number = encryption.calculateForDecode(number, key);
        }

        return number;
    }

    @Override
    public String encode(String text, ArrayList<Integer> keys) {
        for (int index = 0; index < repetition; index++) {
            text = this.encryption.encode(text, getKeysForCurrentEncryption(keys));
        }
        updateKeys(keys);

        return text;
    }

    @Override
    public String decode(String text, ArrayList<Integer> keys) {
        for (int index = 0; index < repetition; index++) {
            text = this.encryption.decode(text, getKeysForCurrentEncryption(keys));
        }
        updateKeys(keys);

        return text;
    }

    @Override
    public void createKey(ArrayList<Integer> keys) {
        this.encryption.createKey(keys);
    }

    @Override
    public void checkIfKeysValid(ArrayList<Integer> keys) throws InvalidEncryptionKeyException {
        this.encryption.checkIfKeysValid(keys);
    }

    @Override
    public int keysAmount() {
        return this.encryption.keysAmount();
    }

    @Override
    public ArrayList<Integer> updateKeys(ArrayList<Integer> keys) {
        for (int index = 0; index < keysAmount(); index++) {

            if (keys.size() > 1) {
                keys.remove(0);
            }
        }

        return keys;
    }

    @Override
    public String name() {
        return EncryptionAlgorithmsNames.REPEAT.label + " of " +
                this.encryption.name() + " with "
                + this.repetition + " repetition";
    }

    private ArrayList<Integer> getKeysForCurrentEncryption(ArrayList<Integer> keys) {
        ArrayList<Integer> keysForCurrentEncryption = new ArrayList<>();

        for (int index = 0; index < keysAmount(); index++) {
            keysForCurrentEncryption.add(keys.get(index));
        }

        return keysForCurrentEncryption;
    }
}

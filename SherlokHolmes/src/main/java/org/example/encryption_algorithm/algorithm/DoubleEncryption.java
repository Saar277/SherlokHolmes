package org.example.encryption_algorithm.algorithm;

import org.example.enums.EncryptionAlgorithmsNames;
import org.example.exception.InvalidEncryptionKeyException;

import java.util.ArrayList;

public class DoubleEncryption extends Encryption {
    private Encryption firstEncryption;
    private Encryption secondEncryption;

    public DoubleEncryption(Encryption firstEncryption, Encryption secondEncryption) {
        this.firstEncryption = firstEncryption;
        this.secondEncryption = secondEncryption;
    }


    @Override
    public int calculateForEncode(int number, int key) {
        return 0;
    }

    @Override
    public int calculateForDecode(int number, int key) {
        return 0;
    }

    @Override
    public String encode(String text, ArrayList<Integer> keys) {
        text = this.firstEncryption.encode(text, keys);
        text = this.secondEncryption.encode(text, keys);

        return text;
    }

    @Override
    public String decode(String text, ArrayList<Integer> keys) {
        text = this.secondEncryption.decode(text, keys);
        text = this.firstEncryption.decode(text, keys);

        return text;
    }

    @Override
    public int keysAmount() {
        return this.firstEncryption.keysAmount() + this.secondEncryption.keysAmount();
    }

    @Override
    public void createKey(ArrayList<Integer> keys) {
        this.firstEncryption.createKey(keys);
        this.secondEncryption.createKey(keys);
    }

    @Override
    public void checkIfKeysValid(ArrayList<Integer> keys) throws InvalidEncryptionKeyException {
        this.firstEncryption.checkIfKeysValid(new ArrayList<>(keys.subList(0, firstEncryption.keysAmount())));
        this.secondEncryption.checkIfKeysValid(new ArrayList<>(keys.subList(firstEncryption.keysAmount(), keys.size())));
    }

    @Override
    public String name() {
        return EncryptionAlgorithmsNames.DOUBLE.label + " of " + this.firstEncryption.name()
                + " and " + this.secondEncryption.name();
    }
}

package org.example.encryption_algorithm.algorithm;

import org.example.exception.EmptyKeysArrayException;
import org.example.exception.InvalidEncryptionKeyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public abstract class Encryption {
    public abstract int calculateForEncode(int number, int key);

    public abstract int calculateForDecode(int number, int key);

    public abstract void createKey(ArrayList<Integer> keys);

    public abstract void checkIfKeysValid(ArrayList<Integer> keys) throws InvalidEncryptionKeyException;

    public abstract String name();

    public String encode(String text, ArrayList<Integer> keys) {
        int key = keys.get(0);
        String encryptedText = iterate(text, character -> calculateForEncode(character, key));

        updateKeys(keys);

        return encryptedText;
    }

    public String decode(String text, ArrayList<Integer> keys) {
        int key = keys.get(0);
        String decryptedText = iterate(text, character -> calculateForDecode(character, key));

        updateKeys(keys);

        return decryptedText;
    }

    public ArrayList<Integer> updateKeys(ArrayList<Integer> keys) {
        if (keys.size() > 1) {
            keys.remove(0);
        }

        return keys;
    }

    public int keysAmount() {
        return 1;
    }

    private static String iterate(String text, Function<Integer, Integer> converter) {
        StringBuilder newText = new StringBuilder();

        for (char c : text.toCharArray()) {
            int charInteger = converter.apply((int) c);
            newText.append((char) charInteger);
        }

        return newText.toString();
    }

    public int keyStrength(ArrayList<Integer> keys) throws EmptyKeysArrayException {
        if (keys.size() == 0) {
            throw new EmptyKeysArrayException();
        }

        int maxKey = Collections.max(keys);
        int minKey = Collections.min(keys);

        int maxKeyLength = String.valueOf(maxKey).replace("-", "").length();
        int minKeyLength = String.valueOf(minKey).replace("-", "").length();

        if (maxKeyLength > minKeyLength) {
            return maxKeyLength;
        } else {
            return minKeyLength;
        }
    }

}
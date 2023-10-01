package org.example.exception;

public class InvalidEncryptionKeyException extends Exception {
    public InvalidEncryptionKeyException(int key) {
        super("the key: " + key + " is not valid");
    }
}

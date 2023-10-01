package org.example.exception;

public class EmptyKeysArrayException extends Exception {
    public EmptyKeysArrayException() {
        super("there are no keys in this encryption");
    }
}

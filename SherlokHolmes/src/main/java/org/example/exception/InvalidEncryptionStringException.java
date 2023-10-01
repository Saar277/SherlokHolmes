package org.example.exception;

public class InvalidEncryptionStringException extends Exception {
    public InvalidEncryptionStringException() {
        super("invalid encryption string");
    }
}

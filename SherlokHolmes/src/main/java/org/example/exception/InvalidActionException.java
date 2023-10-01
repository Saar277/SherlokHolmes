package org.example.exception;

public class InvalidActionException extends Exception {
    public InvalidActionException() {
        super("invalid action");
    }
}

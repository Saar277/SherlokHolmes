package org.example.exception;

public class InvalidDirectoryPathException extends Exception {
    public InvalidDirectoryPathException(String path) {
        super("the path: " + path + " is not directory");
    }
}

package org.example.exception;

public class FileException extends Exception {
    public FileException() {
        super("error while trying to read file");
    }
}

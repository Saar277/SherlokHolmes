package org.example.userio;

public class UserIOInstance {
    public static UserIO userIO = new ConsoleIO();

    public static UserIO getInstance() {
        return userIO;
    }
}

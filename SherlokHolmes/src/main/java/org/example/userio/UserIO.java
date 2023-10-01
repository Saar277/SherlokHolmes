package org.example.userio;

public interface UserIO {
    public abstract String getStringInput();

    public abstract int getIntInput() throws Exception;

    public abstract void print(String toPrint);

    public abstract String printAndGetStringInput(String toPrint);
}

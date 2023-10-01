package org.example.userio;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

public class ConsoleIO implements UserIO {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public String getStringInput() {
        return scanner.nextLine();
    }

    @Override
    public int getIntInput() throws Exception {
        String number = scanner.nextLine();
        if (!NumberUtils.isParsable(number)) {

            throw new Exception("input was not a number");
        }
        return Integer.parseInt(number);
    }

    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public String printAndGetStringInput(String toPrint) {
        this.print(toPrint);
        return this.getStringInput();
    }
}


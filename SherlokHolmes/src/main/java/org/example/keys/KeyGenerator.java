package org.example.keys;

import java.util.Random;

public class KeyGenerator {
    public static int createRandomNumberKey() {
        return (int) ((Math.random() * 101) + 100);
    }

    public static int createPrimeRandomNumber() {
        int randomNumber = 0;
        Random random = new Random();
        randomNumber = random.nextInt(1000) + 1;

        while (!isPrime(randomNumber) || (randomNumber <= 3) || randomNumber / Character.MAX_VALUE != 0) {
            randomNumber = random.nextInt(1000) + 1;
        }

        return randomNumber;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= (number / 2); i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfPrimeRandomKeyValid(int key) {
        return (isPrime(key) && (key != 3 && key / Character.MAX_VALUE == 0));
    }

    public static boolean checkIfRandomNumberKeyValid(int key) {
        return key != 0;
    }
}

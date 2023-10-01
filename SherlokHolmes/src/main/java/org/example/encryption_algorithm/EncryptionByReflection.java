package org.example.encryption_algorithm;

import org.example.encryption_algorithm.algorithm.DoubleEncryption;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_algorithm.algorithm.RepeatEncryption;
import org.example.enums.EncryptionAlgorithmsNames;
import org.example.exception.InvalidEncryptionStringException;

import java.util.Locale;

public class EncryptionByReflection {
    private static final String ENCRYPTION_ALGORITHM_PACKAGE_PATH = "org.example.encryption_algorithm.algorithm";

    public static Encryption convertStringToEncryption(String str)
            throws InvalidEncryptionStringException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int firstEncryptionIndexInString = str.indexOf("[");
        String baseEncryptionString = getBaseEncryptionType(str, firstEncryptionIndexInString);
        str = str.substring(firstEncryptionIndexInString + 1, str.length() - 1);

        switch (EncryptionAlgorithmsNames.valueOf(baseEncryptionString.toUpperCase(Locale.ROOT))) {
            case DOUBLE:
                Encryption firstEncryption = convertStringToEncryption(getFirstEncryption(str));
                Encryption secondEncryption = convertStringToEncryption(getSecondEncryption(str));
                return new DoubleEncryption(firstEncryption, secondEncryption);
            case REPEAT:
                int repetition = Integer.parseInt(getFirstEncryption(str));
                Encryption encryption = convertStringToEncryption(getSecondEncryption(str));
                return new RepeatEncryption(repetition, encryption);
            default:
                Class encryptionClass = Class.forName(
                        ENCRYPTION_ALGORITHM_PACKAGE_PATH + "." + baseEncryptionString + "Encryption");
                Object encryptionObject = encryptionClass.newInstance();

                if (encryptionObject instanceof Encryption) {
                    return (Encryption) encryptionObject;
                } else {
                    throw new InvalidEncryptionStringException();
                }
        }
    }

    private static String getFirstEncryption(String str) {
        int firstEncryptionIndexInString = str.indexOf("], ");
        if (firstEncryptionIndexInString != -1) {
            return str.substring(0, firstEncryptionIndexInString + 1);
        } else {
            return str.substring(0, str.indexOf(","));
        }
    }

    private static String getSecondEncryption(String str) {
        int secondEncryptionIndexInString = str.indexOf("], ");
        if (secondEncryptionIndexInString != -1) {
            return str.substring(secondEncryptionIndexInString + 3);
        } else {
            return str.substring(str.indexOf(" ") + 1);
        }
    }

    private static String getBaseEncryptionType(String str, int firstEncryptionIndexInString) {
        if (firstEncryptionIndexInString == -1) {
            return str;
        } else {
            return str.substring(0, firstEncryptionIndexInString);
        }
    }

}

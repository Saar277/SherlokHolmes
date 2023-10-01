package org.example.encryption_setup;

import org.example.encryption_algorithm.algorithm.*;
import org.example.encryption_setup.json.GenerateEncryptionSetupJson;
import org.example.exception.InvalidActionException;
import org.example.userio.UserIOInstance;

import java.io.File;

import static org.example.finals.EncryptionActionFinals.*;


public class GenerateEncryptionSetup {
    public static EncryptionSetup generate() throws Exception {
        int action = getAction();

        if (action == QUIT_CODE) {
            return new EncryptionSetup(action);
        } else if (action == GET_SETUP_FROM_JSON_FILE) {
            return GenerateEncryptionSetupJson.getSetupFromJson();
        } else if (!checkIfActionValid(action)) {
            throw new InvalidActionException();
        }

        return new EncryptionSetup(action, getEncryption(), getFile());
    }

    public static Encryption getEncryption() {
        return new DoubleEncryption(new RepeatEncryption(7, new XorEncryption()),
                new DoubleEncryption(new ShiftUpEncryption(),
                        new DoubleEncryption(new XorEncryption(), new ShiftUpEncryption())));

    }

    private static File getFile() {
        return new File(UserIOInstance.getInstance().printAndGetStringInput("enter path"));
    }

    private static int getAction() throws Exception {
        String opening = "if you want to ";
        UserIOInstance.getInstance().print(opening + "encrypt a file enter " + ENCRYPT_FILE_CODE);
        UserIOInstance.getInstance().print(opening + "decrypt a file enter " + DECRYPT_FILE_CODE);
        UserIOInstance.getInstance().print(opening + "encrypt a directory async enter " + ENCRYPT_DIRECTORY_ASYNC_CODE);
        UserIOInstance.getInstance().print(opening + "decrypt a directory async enter " + DECRYPT_DIRECTORY_ASYNC_CODE);
        UserIOInstance.getInstance().print(opening + "encrypt a directory sync enter " + ENCRYPT_DIRECTORY_SYNC_CODE);
        UserIOInstance.getInstance().print(opening + "decrypt a directory sync enter " + DECRYPT_DIRECTORY_SYNC_CODE);
        UserIOInstance.getInstance().print(opening + "get setup from json file enter " + GET_SETUP_FROM_JSON_FILE);
        UserIOInstance.getInstance().print(opening + "quit sherlok holmes enter " + QUIT_CODE);

        return UserIOInstance.getInstance().getIntInput();
    }

    private static boolean checkIfActionValid(int action) {
        return action == ENCRYPT_FILE_CODE || action == DECRYPT_FILE_CODE || action == ENCRYPT_DIRECTORY_ASYNC_CODE
                || action == DECRYPT_DIRECTORY_ASYNC_CODE || action == ENCRYPT_DIRECTORY_SYNC_CODE ||
                action == DECRYPT_DIRECTORY_SYNC_CODE;
    }
}

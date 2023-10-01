package org.example.sherlokholmes;

import org.example.encrypt.encrypt_directory.AsyncDirectoryProcessor;
import org.example.encrypt.encrypt_directory.SyncDirectoryProcessor;
import org.example.encrypt.encrypt_file.FileEncryptionFlow;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_setup.EncryptionSetup;
import org.example.enums.EncryptionEvents;
import org.example.event_informers.EncryptionLogEventArgs;
import org.example.exception.InvalidDirectoryPathException;
import org.example.keys.KeysManager;

import static org.example.finals.EncryptionActionFinals.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ActionHandler {
    public static void encrypt(EncryptionSetup config) throws Exception {
        Encryption encryption = config.getEncryption();
        File file = config.getFile();

        switch (config.getAction()) {
            case ENCRYPT_FILE_CODE:
                checkIfFileExist(file);
                EncryptionLogEventArgs args = new EncryptionLogEventArgs(file, encryption.name(),
                        EncryptionEvents.ENCRYPTION_FILE, EncryptionEvents.KEY_FOR_FILE);
                ArrayList<Integer> keys = KeysManager.createKeys(encryption);
                KeysManager.saveKeysInFile(keys, args);
                FileEncryptionFlow.encodeFlow(encryption, args, keys);
                break;
            case ENCRYPT_DIRECTORY_ASYNC_CODE:
                checkIfDirectoryExist(file);
                new AsyncDirectoryProcessor(EncryptionEvents.ENCRYPTION_DIRECTORY, encryption,
                        file.getAbsolutePath()).startEncryption();
                break;
            case ENCRYPT_DIRECTORY_SYNC_CODE:
                checkIfDirectoryExist(file);
                new SyncDirectoryProcessor(EncryptionEvents.ENCRYPTION_DIRECTORY, encryption,
                        file.getAbsolutePath()).startEncryption();
                break;
        }
    }

    public static void decrypt(EncryptionSetup config) throws Exception {
        Encryption encryption = config.getEncryption();
        File file = config.getFile();

        switch (config.getAction()) {
            case DECRYPT_FILE_CODE:
                checkIfFileExist(file);
                EncryptionLogEventArgs args = new EncryptionLogEventArgs(file, encryption.name(),
                        EncryptionEvents.DECRYPTION_FILE, EncryptionEvents.KEY_FOR_FILE);
                ArrayList<Integer> keys = KeysManager.getKeysForDecryption(args);
                FileEncryptionFlow.decodeFlow(encryption, args, keys);
                break;
            case DECRYPT_DIRECTORY_ASYNC_CODE:
                checkIfDirectoryExist(file);
                new AsyncDirectoryProcessor(EncryptionEvents.DECRYPTION_DIRECTORY, encryption,
                        file.getAbsolutePath()).startEncryption();
                break;
            case DECRYPT_DIRECTORY_SYNC_CODE:
                checkIfDirectoryExist(file);
                new SyncDirectoryProcessor(EncryptionEvents.DECRYPTION_DIRECTORY, encryption,
                        file.getAbsolutePath()).startEncryption();
                break;
        }


    }

    private static void checkIfFileExist(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
    }

    private static void checkIfDirectoryExist(File file) throws InvalidDirectoryPathException {
        if (!file.isDirectory()) {
            throw new InvalidDirectoryPathException(file.getAbsolutePath());
        }
    }
}

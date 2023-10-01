package org.example.encrypt.encrypt_directory;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.enums.EncryptionEvents;
import org.example.event_informers.EncryptionLogEventArgs;
import org.example.event_informers.observable.EncryptionEventsObservable;
import org.example.files_utils.DirectoryReader;
import org.example.finals.Finals;
import org.example.keys.KeysManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

public abstract class DirectoryProcessor {
    private static String directoryPath;
    private static Encryption encryptionAlgorithm;
    private static EncryptionEvents action;

    public DirectoryProcessor(EncryptionEvents action, Encryption encryptionAlgorithm, String directoryPath) {
        setAction(action);
        setEncryptionAlgorithm(encryptionAlgorithm);
        setDirectoryPath(directoryPath);
    }

    public abstract void startEncryption() throws Exception;

    public Set<Path> readDirectory() throws IOException {
        return DirectoryReader.getFilesFromDirectory(directoryPath);
    }

    public EncryptionEvents getStartedAction() {
        switch (action) {
            case ENCRYPTION_DIRECTORY:
                return EncryptionEvents.ENCRYPTION_DIRECTORY_STARTED;
            case DECRYPTION_DIRECTORY:
                return EncryptionEvents.DECRYPTION_DIRECTORY_STARTED;
        }
        return null;
    }

    public EncryptionEvents getEndedAction() {
        switch (action) {
            case ENCRYPTION_DIRECTORY:
                return EncryptionEvents.ENCRYPTION_DIRECTORY_ENDED;
            case DECRYPTION_DIRECTORY:
                return EncryptionEvents.DECRYPTION_DIRECTORY_ENDED;
        }
        return null;
    }

    public void notifyDirectoryEncryptionStarted() {
        EncryptionLogEventArgs args = new EncryptionLogEventArgs(new File(directoryPath), this.getStartedAction());

        EncryptionEventsObservable.getInstance().notify(args);
    }

    public void notifyDirectoryEncryptionEnded(long timeStarted) {
        EncryptionLogEventArgs args = new EncryptionLogEventArgs(new File(directoryPath), this.getEndedAction());
        args.setMillisecondsTook(System.currentTimeMillis() - timeStarted);
        args.setEvent(this.getEndedAction()); //check if needed

        EncryptionEventsObservable.getInstance().notify(args);
    }

    public boolean checkIfFileNeedsAction(Path fileName) {
        return (action == EncryptionEvents.DECRYPTION_DIRECTORY ==
                (fileName.toString().endsWith("_encrypted" + Finals.FILE_TYPE)));
    }

    public ArrayList<Integer> getKeys() throws Exception {
        ArrayList<Integer> keys;
        EncryptionLogEventArgs args = this.getArgs(new File(
                this.getFileAbsolutePath(directoryPath, "key" + Finals.FILE_TYPE)));

        switch (action) {
            case ENCRYPTION_DIRECTORY:
                keys = KeysManager.createKeys(encryptionAlgorithm);
                KeysManager.saveKeysInFile(keys, args);
                break;
            case DECRYPTION_DIRECTORY:
                keys = KeysManager.getKeysForDecryption(args);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

        return keys;
    }

    public EncryptionLogEventArgs getArgs(File file) {
        EncryptionLogEventArgs args;

        switch (action) {
            case ENCRYPTION_DIRECTORY:
                args = new EncryptionLogEventArgs(file, encryptionAlgorithm.name(),
                        EncryptionEvents.ENCRYPTION_FILE, EncryptionEvents.KEY_FOR_DIRECTORY);
                break;
            case DECRYPTION_DIRECTORY:
                args = new EncryptionLogEventArgs(file, encryptionAlgorithm.name(),
                        EncryptionEvents.DECRYPTION_FILE, EncryptionEvents.KEY_FOR_DIRECTORY);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

        return args;
    }

    public String getFileAbsolutePath(String filePath, String fileName) {
        return filePath + "/" + fileName;
    }

    public static String getDirectoryPath() {
        return directoryPath;
    }

    public static void setDirectoryPath(String directoryPath) {
        DirectoryProcessor.directoryPath = directoryPath;
    }

    public static Encryption getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public static void setEncryptionAlgorithm(Encryption encryptionAlgorithm) {
        DirectoryProcessor.encryptionAlgorithm = encryptionAlgorithm;
    }

    public static EncryptionEvents getAction() {
        return action;
    }

    public static void setAction(EncryptionEvents action) {
        DirectoryProcessor.action = action;
    }
}

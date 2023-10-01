package org.example.encrypt.encrypt_file;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.enums.EncryptionEvents;
import org.example.event_informers.EncryptionLogEventArgs;
import org.example.event_informers.observable.EncryptionEventsObservable;
import org.example.files_utils.FileCreator;
import org.example.files_utils.FileReader;
import org.example.finals.Finals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileEncryptionFlow {
    public static void encodeFlow(Encryption encryption, EncryptionLogEventArgs args, ArrayList<Integer> keys)
            throws Exception {
        String fileText = FileReader.readFile(args.getFileAbsolutePath());

        args.setEvent(EncryptionEvents.ENCRYPTION_FILE_STARTED);
        EncryptionEventsObservable.getInstance().notify(args);
        long timeStarted = System.currentTimeMillis();

        fileText = encryption.encode(fileText, keys);

        String filePathWithNewFileName = getFilePathWithNewFileName(args.getFileAbsolutePath(), Finals.FILE_TYPE, "_encrypted");
        FileCreator.createFile(new File(filePathWithNewFileName), fileText);

        args.setMillisecondsTook(System.currentTimeMillis() - timeStarted);
        args.setEvent(EncryptionEvents.ENCRYPTION_FILE_ENDED);
        EncryptionEventsObservable.getInstance().notify(args);
    }

    public static void decodeFlow(Encryption encryption, EncryptionLogEventArgs args, ArrayList<Integer> keys)
            throws Exception {
        String fileToDecryptAbsolutePath = getFileToDecryptAbsolutePath(args);
        String fileText = FileReader.readFile(fileToDecryptAbsolutePath);

        ArrayList<Integer> keysForCheck = new ArrayList<>(keys);
        Collections.reverse(keysForCheck);
        encryption.checkIfKeysValid(keysForCheck);

        args.setEvent(EncryptionEvents.DECRYPTION_FILE_STARTED);
        args.setFile(new File(fileToDecryptAbsolutePath));
        EncryptionEventsObservable.getInstance().notify(args);
        long timeStarted = System.currentTimeMillis();

        fileText = encryption.decode(fileText, keys);

        String filePathWithNewFileName = getDecryptedFileAbsolutePath(args);
        FileCreator.createFile(new File(filePathWithNewFileName), fileText.substring(0, fileText.length() - 1));

        args.setMillisecondsTook(System.currentTimeMillis() - timeStarted);
        args.setEvent(EncryptionEvents.DECRYPTION_FILE_ENDED);
        EncryptionEventsObservable.getInstance().notify(args);
    }

    public static String getFilePathWithNewFileName(String fileAbsolutePath, String oldFileName, String newFileName) {
        return fileAbsolutePath.replace(oldFileName, newFileName + Finals.FILE_TYPE);
    }

    public static String getFileNameWithAddedText(String fileName, String textToAdd) {
        String fileNameWithOutFileType = fileName.replace(Finals.FILE_TYPE, "");
        return fileNameWithOutFileType + textToAdd;
    }

    private static String getFileToDecryptAbsolutePath(EncryptionLogEventArgs args) {
        switch (args.getKeysAction()) {
            case KEY_FOR_FILE:
                return getFilePathWithNewFileName(args.getFileAbsolutePath(), Finals.FILE_TYPE, "_encrypted");
            case KEY_FOR_DIRECTORY:
                return args.getFileAbsolutePath();
        }
        return null;
    }

    private static String getDecryptedFileAbsolutePath(EncryptionLogEventArgs args) {
        return getFilePathWithNewFileName(args.getFileAbsolutePath(), "_encrypted" + Finals.FILE_TYPE, "_decrypted");
    }
}

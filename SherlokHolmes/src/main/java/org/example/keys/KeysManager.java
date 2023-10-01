package org.example.keys;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.enums.EncryptionEvents;
import org.example.event_informers.EncryptionLogEventArgs;
import org.example.event_informers.observable.EncryptionEventsObservable;
import org.example.files_utils.FileCreator;
import org.example.files_utils.FileReader;
import org.example.finals.Finals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.example.encrypt.encrypt_file.FileEncryptionFlow.getFileNameWithAddedText;
import static org.example.encrypt.encrypt_file.FileEncryptionFlow.getFilePathWithNewFileName;


public class KeysManager {
    public static ArrayList<Integer> getKeysForDecryption(EncryptionLogEventArgs args) throws Exception {
        String keyFileAbsolutePath = getKeyFileOfFileAbsolutePath(args);
        ArrayList<Integer> keys = getKeysFromFile(keyFileAbsolutePath);
        Collections.reverse(keys);

        return keys;
    }

    private static String getKeyFileOfFileAbsolutePath(EncryptionLogEventArgs args) {
        switch (args.getKeysAction()) {
            case KEY_FOR_FILE:
                return getFilePathWithNewFileName(args.getFileAbsolutePath(), args.getFileName(),
                        getFileNameWithAddedText(args.getFileName(), "_key"));
            case KEY_FOR_DIRECTORY:
                return getFilePathWithNewFileName(args.getFileAbsolutePath(), args.getFileName(), "key");
        }
        return null;
    }

    public static ArrayList<Integer> createKeys(Encryption encryption) {
        ArrayList<Integer> keys = new ArrayList<>();
        encryption.createKey(keys);

        return keys;
    }

    public static void saveKeysInFile(ArrayList<Integer> keys, EncryptionLogEventArgs args) throws IOException {
        String keyFileAbsolutePath = getKeyFileAbsolutePath(args);

        for (int key : keys) {
            FileCreator.createFile(new File(keyFileAbsolutePath), String.valueOf(key));
        }

        args.setEvent(EncryptionEvents.KEY_FILE_CREATED);
        EncryptionEventsObservable.getInstance().notify(args);
    }

    private static ArrayList<Integer> getKeysFromFile(String keyFileAbsolutePath) throws Exception {
        ArrayList<String> keysFromFile = FileReader.readFileToArrayByLines(keyFileAbsolutePath);

        ArrayList<Integer> keys = new ArrayList<>();
        keysFromFile.forEach((key) -> {
            keys.add(Integer.parseInt(key.replace(Finals.NEW_LINE, "")));
        });

        return keys;
    }

    private static String getKeyFileAbsolutePath(EncryptionLogEventArgs args) {
        args.setKeyFilePath(getKeyFileOfFileAbsolutePath(args));

        return args.getKeyFilePath();
    }
}

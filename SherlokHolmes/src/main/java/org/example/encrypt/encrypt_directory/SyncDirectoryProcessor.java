package org.example.encrypt.encrypt_directory;

import org.example.encrypt.encrypt_file.FileEncryptionFlow;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.enums.EncryptionEvents;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

public class SyncDirectoryProcessor extends DirectoryProcessor {
    public SyncDirectoryProcessor(EncryptionEvents action, Encryption encryptionAlgorithm, String directoryPath) {
        super(action, encryptionAlgorithm, directoryPath);
    }

    @Override
    public void startEncryption() throws Exception {
        super.notifyDirectoryEncryptionStarted();
        long timeStarted = System.currentTimeMillis();

        Set<Path> filesNames = super.readDirectory();
        ArrayList<Integer> keys = super.getKeys();

        for (Path fileName : filesNames) {
            if (super.checkIfFileNeedsAction(fileName)) {
                File file = new File(super.getFileAbsolutePath(getDirectoryPath(), fileName.toString()));

                switch (getAction()) {
                    case ENCRYPTION_DIRECTORY:
                        FileEncryptionFlow.encodeFlow(getEncryptionAlgorithm(),
                                super.getArgs(file), new ArrayList<>(keys));
                        break;
                    case DECRYPTION_DIRECTORY:
                        FileEncryptionFlow.decodeFlow(getEncryptionAlgorithm(),
                                super.getArgs(file), new ArrayList<>(keys));
                        break;
                }
            }
        }

        super.notifyDirectoryEncryptionEnded(timeStarted);
    }
}

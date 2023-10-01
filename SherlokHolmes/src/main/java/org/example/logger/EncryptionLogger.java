package org.example.logger;

import org.apache.logging.log4j.*;
import org.example.event_informers.EncryptionLogEventArgs;
import org.example.event_informers.observer.Observer;

public class EncryptionLogger implements Observer<EncryptionLogEventArgs> {
    private static EncryptionLogger encryptionLogger;
    private Logger log4JLogger;

    private EncryptionLogger() {
        this.log4JLogger = Log4JLogger.getInstance(EncryptionLogger.class).getLogger();
    }

    public static Observer getInstance() {
        if (encryptionLogger == null) {
            encryptionLogger = new EncryptionLogger();
        }

        return encryptionLogger;
    }

    private void keyFileCreated(String keyPath) {
        this.log4JLogger.info("key file created");
        this.log4JLogger.info("the file is in: " + keyPath);
    }

    private void encryptionStarted(EncryptionLogEventArgs args) {
        this.log4JLogger.info("Starting to encrypt " + args.getFileName());
    }

    private void encryptionEnded(EncryptionLogEventArgs args) {
        this.log4JLogger.info("Finished Encryption. the encryption for file " + args.getFileName() +
                " with algorithm " + args.getAlgorithm() + " took " + args.getMillisecondsTook() +
                " milliseconds. the encrypted file is located in " + args.getFileAbsolutePath());
    }

    private void decryptionStarted(EncryptionLogEventArgs args) {
        this.log4JLogger.info("Starting to decrypt " + args.getFileName());
    }

    private void decryptionEnded(EncryptionLogEventArgs args) {
        this.log4JLogger.info("Finished Decryption. the decryption for file " + args.getFileName() +
                " with algorithm " + args.getAlgorithm() + " took " + args.getMillisecondsTook() +
                "milliseconds. The decrypted file is located in " + args.getFileAbsolutePath());
    }

    private void encryptDirectoryStarted(String path) {
        this.log4JLogger.info("Encrypt Directory: " + path + " started");
    }

    private void encryptDirectoryEnded(EncryptionLogEventArgs args) {
        this.log4JLogger.info("Encrypt Directory: " + args.getFileAbsolutePath() + " ended and took "
                + args.getMillisecondsTook() + " milliseconds");
    }

    private void decryptDirectoryStarted(String path) {
        this.log4JLogger.info("Decrypt Directory: " + path + " started");
    }

    private void decryptDirectoryEnded(EncryptionLogEventArgs args) {
        this.log4JLogger.info("Decrypt Directory: " + args.getFileAbsolutePath() +
                " ended and took " + args.getMillisecondsTook() + " milliseconds");
    }

    @Override
    public void update(EncryptionLogEventArgs args) {
        switch (args.getEvent()) {
            case ENCRYPTION_FILE_STARTED:
                this.encryptionStarted(args);
                break;
            case ENCRYPTION_FILE_ENDED:
                this.encryptionEnded(args);
                break;
            case DECRYPTION_FILE_STARTED:
                this.decryptionStarted(args);
                break;
            case DECRYPTION_FILE_ENDED:
                this.decryptionEnded(args);
                break;
            case KEY_FILE_CREATED:
                this.keyFileCreated(args.getKeyFilePath());
                break;
            case ENCRYPTION_DIRECTORY_STARTED:
                this.encryptDirectoryStarted(args.getFileAbsolutePath());
                break;
            case ENCRYPTION_DIRECTORY_ENDED:
                this.encryptDirectoryEnded(args);
                break;
            case DECRYPTION_DIRECTORY_STARTED:
                this.decryptDirectoryStarted(args.getFileAbsolutePath());
                break;
            case DECRYPTION_DIRECTORY_ENDED:
                this.decryptDirectoryEnded(args);
                break;
        }
    }
}

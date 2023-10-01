package org.example.runnables;

import org.example.encrypt.encrypt_file.FileEncryptionFlow;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.event_informers.EncryptionLogEventArgs;
import org.example.logger.Log4JLogger;

import java.util.ArrayList;

public class EncryptionRunnable implements Runnable {
    private Encryption encryptionAlgorithm;
    private EncryptionLogEventArgs args;
    private ArrayList<Integer> keys;

    public EncryptionRunnable(Encryption encryptionAlgorithm, EncryptionLogEventArgs args, ArrayList<Integer> keys) {
        this.setEncryptionAlgorithm(encryptionAlgorithm);
        this.setArgs(args);
        this.setKeys(keys);
    }


    @Override
    public void run() {
        try {
            switch (args.getEvent()) {
                case ENCRYPTION_FILE:
                    FileEncryptionFlow.encodeFlow(encryptionAlgorithm, args, keys);
                    break;
                case DECRYPTION_FILE:
                    FileEncryptionFlow.decodeFlow(encryptionAlgorithm, args, keys);
            }
        } catch (Exception exception) {
            Log4JLogger.getInstance(EncryptionRunnable.class).
                    error(exception.getMessage());
        }
    }

    public Encryption getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(Encryption encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public EncryptionLogEventArgs getArgs() {
        return args;
    }

    public void setArgs(EncryptionLogEventArgs args) {
        this.args = args;
    }

    public ArrayList<Integer> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Integer> keys) {
        this.keys = keys;
    }
}

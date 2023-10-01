package org.example.event_informers;

import org.example.enums.EncryptionEvents;

import java.io.File;

public class EncryptionLogEventArgs {
    private File file;
    private String algorithm;
    private long millisecondsTook;
    private EncryptionEvents event;
    private String keyFilePath;
    private EncryptionEvents keysAction;

    public EncryptionLogEventArgs(File file, String algorithm, EncryptionEvents event, EncryptionEvents keysAction) {
        this.setFile(file);
        this.setAlgorithm(algorithm);
        this.setEvent(event);
        this.setKeysAction(keysAction);
    }

    public EncryptionLogEventArgs(File file, EncryptionEvents event) {
        this.setFile(file);
        this.setEvent(event);
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }

    public String getFileAbsolutePath() {
        return file.getAbsolutePath();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public long getMillisecondsTook() {
        return millisecondsTook;
    }

    public void setMillisecondsTook(long millisecondsTook) {
        this.millisecondsTook = millisecondsTook;
    }

    public EncryptionEvents getEvent() {
        return event;
    }

    public void setEvent(EncryptionEvents event) {
        this.event = event;
    }

    public String getKeyFilePath() {
        return keyFilePath;
    }

    public void setKeyFilePath(String keyFilePath) {
        this.keyFilePath = keyFilePath;
    }

    public EncryptionEvents getKeysAction() {
        return keysAction;
    }

    public void setKeysAction(EncryptionEvents keysAction) {
        this.keysAction = keysAction;
    }
}

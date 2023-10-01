package org.example.encryption_setup;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.enums.EncryptionEvents;

import static org.example.finals.EncryptionActionFinals.*;

import java.io.File;

public class EncryptionSetup {
    private int action;
    private Encryption encryption;
    private File file;

    public EncryptionSetup(int action, Encryption encryption, File file) {
        this.setAction(action);
        this.setEncryption(encryption);
        this.setFile(file);
    }

    public EncryptionSetup(int action) {
        this.setAction(action);
    }

    public EncryptionEvents getActionType() {
        if (action == ENCRYPT_FILE_CODE || action == ENCRYPT_DIRECTORY_ASYNC_CODE ||
                action == ENCRYPT_DIRECTORY_SYNC_CODE) {
            return EncryptionEvents.ENCRYPT;
        } else {
            return EncryptionEvents.DECRYPT;
        }
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

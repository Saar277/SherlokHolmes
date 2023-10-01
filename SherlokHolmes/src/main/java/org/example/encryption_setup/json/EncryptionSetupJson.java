package org.example.encryption_setup.json;

public class EncryptionSetupJson {
    private int action;
    private String encryption;
    private String path;

    public EncryptionSetupJson(int action, String encryption, String path) {
        this.setAction(action);
        this.setEncryption(encryption);
        this.setPath(path);
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

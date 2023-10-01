package org.example.sherlokholmes;

import org.example.encryption_setup.EncryptionSetup;

import static org.example.finals.EncryptionActionFinals.*;

public class EncryptionManager {
    public static boolean start(EncryptionSetup setup) throws Exception {
        int action = setup.getAction();

        if (action == QUIT_CODE) {
            return false;
        }

        switch (setup.getActionType()) {
            case ENCRYPT:
                ActionHandler.encrypt(setup);
                break;
            case DECRYPT:
                ActionHandler.decrypt(setup);
                break;
        }

        return true;
    }
}

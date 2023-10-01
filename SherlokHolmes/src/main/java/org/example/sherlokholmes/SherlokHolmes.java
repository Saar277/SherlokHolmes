package org.example.sherlokholmes;

import org.example.encryption_setup.GenerateEncryptionSetup;
import org.example.logger.Log4JLogger;
import org.example.userio.UserIOInstance;

public class SherlokHolmes {
    public static void start() {
        boolean shouldContinue = true;

        while (shouldContinue) {
            try {
                shouldContinue = EncryptionManager.start(GenerateEncryptionSetup.generate());
            } catch (Exception exception) {
                UserIOInstance.getInstance().print("An error occurred. try again");
                Log4JLogger.getInstance(SherlokHolmes.class).error(exception.getMessage());
            }
        }
    }
}

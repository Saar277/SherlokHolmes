package org.example.event_informers.observable;

import org.example.logger.EncryptionLogger;

public class ObservablesManager {
    private static ObservablesManager observablesManager;
    private static EncryptionEventsObservable encryptionEventsObservable;

    private ObservablesManager() {
        encryptionEventsObservable = EncryptionEventsObservable.getInstance();
    }

    public static ObservablesManager getInstance() {
        if (observablesManager == null) {
            observablesManager = new ObservablesManager();
        }

        return observablesManager;
    }

    public void applyRegisters() {
        encryptionEventsObservable.addObserverToList(EncryptionLogger.getInstance());
    }
}

package org.example.event_informers.observable;

import org.example.event_informers.EncryptionLogEventArgs;
import org.example.event_informers.observer.Observer;

import java.util.ArrayList;

public class EncryptionEventsObservable {
    private static EncryptionEventsObservable encryptionEventsObservable;
    private ArrayList<Observer<EncryptionLogEventArgs>> observerList;

    private EncryptionEventsObservable() {
        observerList = new ArrayList<>();
    }

    public static EncryptionEventsObservable getInstance() {
        if (encryptionEventsObservable == null) {
            encryptionEventsObservable = new EncryptionEventsObservable();
        }

        return encryptionEventsObservable;
    }

    public void addObserverToList(Observer<EncryptionLogEventArgs> observer) {
        this.observerList.add(observer);
    }

    public void notify(EncryptionLogEventArgs args) {
        for (Observer<EncryptionLogEventArgs> observer : this.observerList) {
            observer.update(args);
        }
    }
}

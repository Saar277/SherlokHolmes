package org.example;

import org.example.event_informers.observable.ObservablesManager;
import org.example.sherlokholmes.SherlokHolmes;
import org.example.userio.UserIOInstance;

public class Main {
    public static void main(String[] args) {
        ObservablesManager.getInstance().applyRegisters();

        UserIOInstance.getInstance().print("Hey! welcome to sherlok holmes");
        SherlokHolmes.start();
    }
}
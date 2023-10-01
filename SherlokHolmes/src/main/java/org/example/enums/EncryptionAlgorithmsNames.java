package org.example.enums;

public enum EncryptionAlgorithmsNames {
    DOUBLE("Double"),
    REPEAT("Repeat"),
    SHIFTMULTIPLY("ShiftMultiply"),
    SHIFTUP("ShiftUp"),
    XOR("Xor");

    public final String label;

    private EncryptionAlgorithmsNames(String label) {
        this.label = label;
    }
}

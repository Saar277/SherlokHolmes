package org.example.encryption_setup.json;

import com.google.gson.Gson;
import org.example.encryption_algorithm.EncryptionByReflection;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_setup.EncryptionSetup;
import org.example.exception.InvalidEncryptionStringException;
import org.example.userio.UserIOInstance;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GenerateEncryptionSetupJson {
    public static EncryptionSetup getSetupFromJson() throws IOException, InvalidEncryptionStringException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        Reader reader = new FileReader(UserIOInstance.getInstance().
                printAndGetStringInput("enter json setup file path"));
        Gson gson = new Gson();

        EncryptionSetupJson setup = gson.fromJson(reader, EncryptionSetupJson.class);
        Encryption encryption = EncryptionByReflection.convertStringToEncryption(setup.getEncryption());
        File file = new File(setup.getPath());

        return new EncryptionSetup(setup.getAction(), encryption, file);
    }
}

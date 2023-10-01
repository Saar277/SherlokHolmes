package org.example.files_utils;

import org.example.finals.Finals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static void createFile(File file, String fileText) throws IOException {
        boolean isFileExists = file.exists();

        if (!isFileExists) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        if (isFileExists) {
            bufferedWriter.write(Finals.NEW_LINE + fileText);
        } else {
            bufferedWriter.write(fileText);
        }

        bufferedWriter.close();
    }
}

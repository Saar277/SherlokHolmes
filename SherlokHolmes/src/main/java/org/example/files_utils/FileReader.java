package org.example.files_utils;

import org.example.exception.FileException;
import org.example.finals.Finals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {
    public static ArrayList<String> readFileToArrayByLines(String fileAbsolutePath) throws Exception {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileAbsolutePath))) {
            ArrayList<String> fileData = new ArrayList<>();
            String currLine;

            while ((currLine = reader.readLine()) != null) {
                fileData.add(currLine + Finals.NEW_LINE);
            }

            return fileData;
        } catch (FileNotFoundException exception) {
            throw exception;
        } catch (IOException exception) {
            throw new FileException();
        }
    }

    public static String readFile(String fileAbsolutePath) throws Exception {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileAbsolutePath))) {
            String fileData = "";
            String currLine;

            while ((currLine = reader.readLine()) != null) {
                fileData += currLine + Finals.NEW_LINE;
            }

            return fileData;
        } catch (FileNotFoundException exception) {
            throw exception;
        } catch (IOException exception) {
            throw new FileException();
        }
    }
}

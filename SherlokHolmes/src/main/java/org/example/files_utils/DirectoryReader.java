package org.example.files_utils;

import org.example.finals.Finals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryReader {
    public static Set<Path> getFilesFromDirectory(String directoryPath) throws IOException {
        Stream<Path> stream = Files.list(Paths.get(directoryPath));
        return stream
                .filter(file -> !Files.isDirectory(file))
                .filter(file -> file.toString().endsWith(Finals.FILE_TYPE))
                .map(Path::getFileName)
                .collect(Collectors.toSet());
    }
}

package files_utils;

import org.example.files_utils.DirectoryReader;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DirectoryReaderTest {
    private static final String FIRST_FILE_FOR_TEST_NAME = "firstFileForTest.txt";
    private static final String SECOND_FILE_FOR_TEST_NAME = "secondFileForTest.txt";
    private static final String baseDirectoryPath = "src/main/resources";
    private static String directoryPath;

    @Test
    public void getFilesFromDirectory_directoryWithFiles() throws IOException {
        directoryPath = baseDirectoryPath + "/files_for_test/with_files";

        Set<Path> actual = DirectoryReader.getFilesFromDirectory(directoryPath);

        Set<Path> expected = new HashSet<>();
        expected.add(Path.of(FIRST_FILE_FOR_TEST_NAME));
        expected.add(Path.of(SECOND_FILE_FOR_TEST_NAME));

        assertEquals(expected, actual);
    }

    @Test
    public void getFilesFromDirectory_emptyDirectory() throws IOException {
        directoryPath = baseDirectoryPath + "/files_for_test/without_files";

        int actual = DirectoryReader.getFilesFromDirectory(directoryPath).size();
        final int EXPECTED = 0;

        assertEquals(EXPECTED, actual);
    }

    @Test(expected = NoSuchFileException.class)
    public void getFilesFromDirectory_noExistDirectory() throws IOException {
        directoryPath = baseDirectoryPath + "/m";

        DirectoryReader.getFilesFromDirectory(directoryPath);
    }
}

package files_utils;

import org.example.files_utils.FileCreator;
import org.example.files_utils.FileReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private static final String FILE_PATH = "src/main/resources/";
    private static final String REGULAR_FILE_NAME = "fileForReaderTest.txt";
    private static final String EMPTY_FILE_NAME = "emptyFile.txt";
    private static final File REGULAR_FILE = new File(FILE_PATH + REGULAR_FILE_NAME);
    private static final File EMPTY_FILE = new File(FILE_PATH + EMPTY_FILE_NAME);

    @BeforeClass
    public static void before() throws IOException {
        FileCreator.createFile(REGULAR_FILE, "THE\nWORLD\nIS\nYOURS\n");
        FileCreator.createFile(EMPTY_FILE, "");
    }

    @AfterClass
    public static void after() {
        REGULAR_FILE.delete();
        EMPTY_FILE.delete();
    }

    @Test
    public void readFileToArrayByLines_regular() throws Exception {
        ArrayList<String> actual = FileReader.readFileToArrayByLines(REGULAR_FILE.getAbsolutePath());

        ArrayList<String> expected = new ArrayList<>();
        expected.add("THE\n");
        expected.add("WORLD\n");
        expected.add("IS\n");
        expected.add("YOURS\n");

        assertEquals(expected, actual);
    }

    @Test
    public void readFile_regular() throws Exception {
        String actual = FileReader.readFile(REGULAR_FILE.getAbsolutePath());

        final String EXPECTED = "THE\nWORLD\nIS\nYOURS\n";

        assertEquals(EXPECTED, actual);
    }

    @Test(expected = FileNotFoundException.class)
    public void readFile_WhenFileNotExist() throws Exception {
        FileReader.readFile("/a/b/c");
    }

    @Test
    public void readFile_emptyFile() throws Exception {
        String actual = FileReader.readFile(EMPTY_FILE.getAbsolutePath());
        final String EXPECTED = "";

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void readFileToArrayByLines_emptyFile() throws Exception {
        ArrayList<String> actual = FileReader.readFileToArrayByLines(EMPTY_FILE.getAbsolutePath());
        final ArrayList<String> EXPECTED = new ArrayList<>();

        assertEquals(EXPECTED, actual);
    }

    @Test(expected = FileNotFoundException.class)
    public void readFile_WhenPathIsDirectory() throws Exception {
        FileReader.readFile(FILE_PATH);
    }
}
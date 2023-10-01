package files_utils;

import org.example.files_utils.FileCreator;
import org.example.files_utils.FileReader;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileCreatorTest {
    private static String FILE_DATA = "TEST TEST TEST";
    private final static String FILE_ABSOLUTE_PATH = "src/main/resources/fileCreatorTest.txt";
    private static File FILE = new File(FILE_ABSOLUTE_PATH);

    @After
    public void after() {
        FILE.delete();
    }

    @Test
    public void createFile_regular() throws Exception {
        FileCreator.createFile(FILE, FILE_DATA);

        assertTrue(FILE.exists());

        String actual = FileReader.readFile(FILE.getAbsolutePath());
        final String EXPECTED = "TEST TEST TEST\n";

        assertEquals(EXPECTED, actual);
    }

    @Test
    public void createFile_whenFileExists() throws Exception {
        FileCreator.createFile(FILE, FILE_DATA);

        assertTrue(FILE.exists());

        FileCreator.createFile(FILE, "THE WORLD IS YOURS!!!");

        String actual = FileReader.readFile(FILE.getAbsolutePath());
        final String EXPECTED = "TEST TEST TEST\nTHE WORLD IS YOURS!!!\n";

        assertEquals(EXPECTED, actual);
    }

    @Test(expected = IOException.class)
    public void createFile_withNoExistPath() throws IOException {
        File file = new File("a/b/c");

        FileCreator.createFile(file, FILE_DATA);
    }
}

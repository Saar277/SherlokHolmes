package encryption.encrypt_directory;

import org.apache.commons.io.FileUtils;
import org.example.encrypt.encrypt_directory.AsyncDirectoryProcessor;
import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.encryption_algorithm.algorithm.ShiftUpEncryption;
import org.example.enums.EncryptionEvents;
import org.example.files_utils.FileCreator;
import org.example.files_utils.FileReader;
import org.example.finals.Finals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AsyncDirectoryProcessorTest {
    private static final File DIRECTORY = new File("src/main/resources/files_to_encrypt");
    private static final File FIRST_FILE = new File(DIRECTORY.getAbsoluteFile() + "/a" + Finals.FILE_TYPE);
    private static final String FIRST_FILE_TEXT = "SAAR";
    private static final File SECOND_FILE = new File(DIRECTORY.getAbsoluteFile() + "/b" + Finals.FILE_TYPE);
    private static final String SECOND_FILE_TEXT = "BAR";

    @Before
    public void before() throws IOException {
        DIRECTORY.mkdir();
        FileCreator.createFile(FIRST_FILE, FIRST_FILE_TEXT);
        FileCreator.createFile(SECOND_FILE, SECOND_FILE_TEXT);
    }

    @After
    public void after() throws IOException {
        FileUtils.cleanDirectory(DIRECTORY);
        DIRECTORY.delete();
    }

    @Test
    public void startEncryption_regular() throws Exception {
        Encryption encryption = new ShiftUpEncryption();

        new AsyncDirectoryProcessor(EncryptionEvents.ENCRYPTION_DIRECTORY, encryption,
                DIRECTORY.getAbsolutePath()).startEncryption();

        new AsyncDirectoryProcessor(EncryptionEvents.DECRYPTION_DIRECTORY, encryption,
                DIRECTORY.getAbsolutePath()).startEncryption();

        String firstDecryptedFileText = FileReader.readFile(DIRECTORY.getAbsolutePath()
                + "/a_decrypted" + Finals.FILE_TYPE);

        assertEquals(FIRST_FILE_TEXT, firstDecryptedFileText.substring(0, firstDecryptedFileText.length() - 1));

        String secondDecryptedFileText = FileReader.readFile(DIRECTORY.getAbsolutePath()
                + "/b_decrypted" + Finals.FILE_TYPE);

        assertEquals(SECOND_FILE_TEXT, secondDecryptedFileText.substring(0, secondDecryptedFileText.length() - 1));
    }
}

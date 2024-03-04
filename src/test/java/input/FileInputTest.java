package input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileInputTest {

    private final String testFileName = "testFile.txt";
    private File testFile;

    /**
     * Sets up the environment before each test.
     * This method creates a temporary file with predefined content for testing purposes.
     *
     * @throws IOException if an I/O error occurs while writing to the test file.
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary test file
        testFile = new File(testFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        writer.write("Test line 1\nTest line 2");
        writer.close();
    }

    /**
     * Cleans up the environment after each test.
     * This method deletes the temporary test file created for testing purposes.
     */
    @AfterEach
    public void tearDown() {
        // Delete the test file after each test
        testFile.delete();
    }

    /**
     * Tests reading from an existing file.
     * This test verifies that the {@link FileInput#readLine()} method correctly reads the first line from a file.
     */
    @Test
    public void testFileExists() {
        FileInput fileInput = new FileInput(testFileName);
        assertEquals("Test line 1", fileInput.readLine(), "The first line should match the expected output.");
    }

    /**
     * Tests the behavior when the specified file does not exist.
     * This test checks that a {@link RuntimeException} is thrown when trying to create a {@link FileInput} instance with a non-existing file path.
     */
    @Test
    public void testFileDoesNotExist() {
        assertThrows(RuntimeException.class, () -> new FileInput("nonExistingFile.txt"), "Should throw RuntimeException due to FileNotFoundException.");
    }

    /**
     * Tests the error handling of the {@link FileInput#readLine()} method.
     * This test simulates a read error by forcibly closing the BufferedReader before calling {@link FileInput#readLine()}, expecting a {@link RuntimeException} to be thrown.
     */
    @Test
    public void testReadLineError() {
        FileInput fileInput = new FileInput(testFileName);
        try {
            fileInput.reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThrows(RuntimeException.class, fileInput::readLine, "Should throw RuntimeException due to IOException in readLine.");
    }
}

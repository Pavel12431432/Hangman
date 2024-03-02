package output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link OutputFile}.
 */
public class OutputFileTest {

    @TempDir
    File tempDir;

    private OutputFile outputFile;
    private Path testFilePath;

    /**
     * Sets up the test environment before each test. Initializes {@link OutputFile} with a test file path.
     */
    @BeforeEach
    public void setUp() {
        testFilePath = new File(tempDir, "testFile.txt").toPath();
        outputFile = new OutputFile(testFilePath.toString());
    }

    /**
     * Tests that when the specified file does not exist, the {@link OutputFile#outputLine(String)} method creates it
     * and writes the provided line to the new file.
     * @throws IOException if an I/O error occurs when opening the file for reading its contents
     */
    @Test
    public void testOutputLineCreatesFileIfNotExist() throws IOException {
        String testLine = "Test line";
        outputFile.outputLine(testLine);

        assertTrue(Files.exists(testFilePath), "File should be created");

        try (BufferedReader reader = Files.newBufferedReader(testFilePath)) {
            assertEquals(testLine, reader.readLine(), "The written line should match the input");
        }
    }

    /**
     * Tests that when the specified file exists, the {@link OutputFile#outputLine(String)} method appends the provided
     * line to the existing file without overwriting its content.
     * @throws IOException if an I/O error occurs when opening the file for reading its contents
     */
    @Test
    public void testOutputLineAppendsToFileIfExist() throws IOException {
        Files.write(testFilePath, "Initial line\n".getBytes());

        String testLine = "Test line";
        outputFile.outputLine(testLine);

        try (BufferedReader reader = Files.newBufferedReader(testFilePath)) {
            reader.readLine(); // Skip the initial line
            assertEquals(testLine, reader.readLine(), "The second line should be the appended line");
        }
    }

    /**
     * Tests that the {@link OutputFile#outputLine(String)} method throws a RuntimeException when an IOException occurs
     * during the file writing process. This test simulates a scenario where the file is not writable by attempting
     * to write to a directory path instead of a file.
     */
    @Test
    public void testOutputLineThrowsExceptionOnIOException() {
        outputFile = new OutputFile(tempDir.toString()); // Using a directory path to induce an IOException

        assertThrows(RuntimeException.class, () -> outputFile.outputLine("Test line"),
                "Writing to a directory should throw a RuntimeException");
    }
}
package test.output;

import main.output.Output;
import main.output.OutputFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutputFileTest {

    private Path tempFile;

    /**
     * Sets up a temporary file for test.
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file which will be used for testing
        tempFile = Files.createTempFile("testOutputFile", ".txt");
    }

    /**
     * Cleans up by deleting the temporary file.
     */
    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file after each test
        Files.deleteIfExists(tempFile);
    }

    /**
     * Verifies output line is written to file correctly.
     */
    @Test
    public void testOutputLine() throws IOException {
        // Given
        Output outputFile = new OutputFile(tempFile.toString());
        String testLine = "Test line for OutputFile";

        // When
        outputFile.outputLine(testLine);

        // Then
        assertTrue(Files.exists(tempFile), "File should exist");

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile.toFile()))) {
            String line = reader.readLine();
            assertEquals(testLine, line, "The written line should match the input line");
        }
    }
}
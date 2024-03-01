package output;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class OutputConsoleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Redirects System.out to capture console output.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restores System.out to its original state.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Tests if the console output matches expected string.
     */
    @Test
    public void testOutputLine() {
        Output console = new OutputConsole();
        String testString = "Hello, JUnit!";
        console.outputLine(testString);

        // Check that the correct output was printed to the console
        Assertions.assertEquals(testString + System.lineSeparator(), outContent.toString());
    }
}
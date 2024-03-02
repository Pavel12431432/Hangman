package output;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

/**
 * Tests for the {@link OutputConsole} class.
 */
public class OutputConsoleTest {

    @Mock
    private Logger mockLogger;

    private OutputConsole outputConsole;

    /**
     * Sets up the test environment before each test.
     * This method initializes the mocks and creates a new instance of {@link OutputConsole}
     * with a mocked {@link Logger}.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        outputConsole = new OutputConsole();
        OutputConsole.logger = mockLogger; // Inject mock logger into OutputConsole class
    }

    /**
     * Tests that a single line of text is correctly passed to the logger.
     * This test verifies that when {@link OutputConsole#outputLine(String)} is called,
     * the logger's {@code info} method is invoked with the same string.
     */
    @Test
    public void testOutputLineCallsLoggerWithCorrectLine() {
        String testLine = "Test line";
        outputConsole.outputLine(testLine);
        verify(mockLogger).info(testLine); // Verify logger.info was called with "Test line"
    }
}
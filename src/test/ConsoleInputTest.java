package test;

import main.input.ConsoleInput;
import main.input.InputReader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

/**
 * JUnit tests for the {@link ConsoleInput} class.
 */
public class ConsoleInputTest {
    /**
     * Test the {@link ConsoleInput#readLine() readLine()} method of the {@link ConsoleInput} class.
     */
    @Test
    public void readLineTest() {
        String input = "a\n5\nk\nabc\nass  sd\n)\np";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputReader inputTest = new ConsoleInput();

        assert "a".equals(inputTest.readLine());
        assert "5".equals(inputTest.readLine());
        assert "k".equals(inputTest.readLine());
        assert "abc".equals(inputTest.readLine());
        assert "ass  sd".equals(inputTest.readLine());
        assert ")".equals(inputTest.readLine());
        assert "p".equals(inputTest.readLine());
    }
}

package test.input;

import main.input.FileInput;
import main.input.InputReader;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the {@link FileInput} class.
 */
public class FileInputTest {
    /**
     * Test the {@link FileInput#readLine() readLine()} method of the {@link FileInput} class.
     */
    @Test
    public void readLineTest() {
        InputReader inputTest = new FileInput("src/test/input/testInput.txt");
        assert "abc".equals(inputTest.readLine());
        assert "asd f sfgdfg".equals(inputTest.readLine());
        assert "".equals(inputTest.readLine());
        assert "...l.'".equals(inputTest.readLine());
    }
}

package main.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that is used to read input from the console, implements the {@link InputReader} interface.
 */
public class ConsoleInput implements InputReader{
    BufferedReader reader;
    /**
     * Constructs a {@link ConsoleInput} object and initializes a new BufferedReader.
     */
    public ConsoleInput() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads the next line from the console.
     * @return line, read from the console.
     */
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

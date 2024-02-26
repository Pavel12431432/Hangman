package main.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that is used to read input from a file, implements the {@link InputReader} interface.
 */
public class FileInput implements InputReader{
    BufferedReader reader;
    /**
     * Constructs a {@link FileInput} object and initializes a new BufferedReader.
     * @param path path to the file.
     */
    public FileInput(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Reads the next line from the file.
     * @return line, read from the file.
     */
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package output;

/**
 * The {@code OutputFile} class implements the {@code Output} interface to write output lines to a specified file.
 * It appends each output line to the end of the file, ensuring that previous content is not overwritten.
 */
public interface Output {
    void outputLine(String line);
}
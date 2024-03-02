package output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * The {@code OutputFile} class implements the {@code Output} interface to write output lines to a specified file.
 * It appends each output line to the end of the file, ensuring that previous content is not overwritten.
 */
public class OutputFile implements Output {
    public final Path filePath;
    /**
     * Constructs a new {@code OutputFile} instance with the specified file path.
     *
     * @param filePath the path of the file where output lines will be written. The file will be created if it does not exist,
     *                 and output lines will be appended if the file already exists.
     */
    public OutputFile(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Writes a single line of output to the file specified by {@code filePath}. This method appends the line to the end
     * of the file, followed by a new line character, ensuring that each call to this method starts on a new line.
     *
     * @param line the line of text to be written to the file. This should not include the new line character, as it is
     *             automatically added.
     */
    @Override
    public void outputLine(String line) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + filePath, e);
        }
    }
}
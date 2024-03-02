package output;

import com.sun.tools.javac.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code OutputConsole} class implements the {@code Output} interface to write output lines to the console.
 * This class provides a simple way to output text to the standard output stream, typically a terminal or command prompt.
 */
public class OutputConsole implements Output {
    static Logger logger = LogManager.getLogger(Main.class);
    /**
     * Writes a single line of output to the console. This method simply prints the provided line of text to
     * the standard output stream using {@code System.out.println}.
     *
     * @param line the line of text to be printed to the console. The method ensures that the output is followed by a new line,
     *             keeping subsequent calls to this method on separate lines.
     */
    @Override
    public void outputLine(String line) {
        logger.info(line);
    }
}
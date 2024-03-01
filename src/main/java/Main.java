import input.ConsoleInput;
import input.InputReader;
import output.Output;
import output.OutputConsole;

/**
 * The Main class contains the main method to run the Hangman game.
 */
public class Main {
    /**
     * The entry point for the Hangman game.
     */
    public static void main(String[] args) {
        InputReader input = new ConsoleInput();
        Output out = new OutputConsole();
        Hangman hangmanGame = new Hangman(input.readLine());

        hangmanGame.playGame(input, out);
    }
}
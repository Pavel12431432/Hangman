package main;

import main.input.FileInput;
import main.input.InputReader;
import main.output.Output;
import main.output.OutputFile;

/**
 * The Main class contains the main method to run the Hangman game.
 */
public class Main {
    /**
     * The entry point for the Hangman game.
     */
    public static void main(String[] args) {
        InputReader input = new FileInput("src/main/input/input.txt");
        Output out = new OutputFile("src/main/output/output.txt");
        Hangman hangmanGame = new Hangman(input.readLine());

        hangmanGame.playGame(input, out);
    }
}
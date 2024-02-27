package main;

import main.input.FileInput;
import main.input.InputReader;
import main.output.Output;
import main.output.OutputConsole;

/**
 * The Main class contains the main method to run the Hangman game.
 */
public class Main {
    /**
     * The entry point for the Hangman game.
     */
    public static void main(String[] args) {
        InputReader input = new FileInput("src/main/input/input.txt");
        Output out = new OutputConsole();
        Hangman hangmanGame = new Hangman("accident");

        hangmanGame.playGame(input, out);
    }
}
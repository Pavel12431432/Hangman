package main;

import main.input.ConsoleInput;
import main.input.FileInput;
import main.input.InputReader;

/**
 * The Main class contains the main method to run the Hangman game.
 */
public class Main {
    /**
     * The entry point for the Hangman game.
     */
    public static void main(String[] args) {
        InputReader input = new FileInput("src/main/input/input.txt");
        Hangman hangmanGame = new Hangman("accident");

        hangmanGame.playGame(input);
    }
}

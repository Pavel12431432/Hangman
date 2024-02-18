package main;

import java.util.Scanner;

/**
 * The Main class contains the main method to run the Hangman game.
 */
public class Main {
    /**
     * The entry point for the Hangman game.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hangman hangmanGame = new Hangman("accident");

        hangmanGame.playGame(sc);
    }
}

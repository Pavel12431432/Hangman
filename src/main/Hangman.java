package main;

import java.util.Scanner;

/**
 * The Hangman class represents a Hangman game.
 */
public class Hangman {
    private Word currentWord;
    private int attemptsLeft = 10;

    /**
     * Constructs a Hangman game with the specified word.
     *
     * @param newWord The word for the Hangman game.
     */
    public Hangman(String newWord) {
        setWord(newWord);
    }

    /**
     * Gets the current state of the game, including the masked word and attempts left.
     *
     * @return The current game state as a formatted string.
     */
    public String getGameState() {
        return currentWord.getMaskedWord() + "\t" + attemptsLeft + " attempts left";
    }

    /**
     * Concludes the game and returns the result message.
     *
     * @return The result message indicating whether the player won or lost.
     */
    public String concludeGame() {
        if (isGameOver()) {
            return "You lose! The word was: " + currentWord.getWord();
        } else {
            return "You win!";
        }
    }

    /**
     * Processes a guessed letter and updates the game state.
     *
     * @param guessedLetter The letter guessed by the player.
     * @return A message indicating whether the guess was correct or incorrect.
     */
    public String processGuess(char guessedLetter) {
        if (!currentWord.getWordLetters().contains(guessedLetter)) {
            attemptsLeft--;
            return "Incorrect guess.";
        }
        currentWord.revealLetter(guessedLetter);
        return "Good guess!";
    }

    /**
     * Prompts the user for a letter input.
     *
     * @param sc The Scanner object for input.
     * @return The guessed letter provided by the user.
     */
    public char promptForLetter(Scanner sc) {
        char guessedLetter;
        while (true) {
            try {
                guessedLetter = inputChar(sc.nextLine());
                if (currentWord.getGuessedLetters().contains(guessedLetter)) {
                    System.out.println("Letter is already guessed.");
                } else {
                    return guessedLetter;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Plays the Hangman game until the word is guessed or the player runs out of attempts.
     *
     * @param sc The Scanner object for input.
     */
    public void playGame(Scanner sc) {
        System.out.println(getGameState());
        while (!currentWord.isWordGuessed() && !this.isGameOver()) {
            char guessedChar = promptForLetter(sc);
            System.out.println(processGuess(guessedChar));
            System.out.println(getGameState());
        }
        System.out.println(concludeGame());
    }

    /**
     * Converts a string input to a character and performs validation.
     *
     * @param lineInput The input string to convert to a character.
     * @return The converted character.
     * @throws IllegalArgumentException If the input is not a single lowercase letter.
     */
    public static char inputChar(String lineInput) {
        if (lineInput.length() != 1)
            throw new IllegalArgumentException("Input exactly 1 character");
        if ('a' > lineInput.charAt(0) || 'z' < lineInput.charAt(0))
            throw new IllegalArgumentException("Input a lowercase letter");
        return lineInput.charAt(0);
    }

    /**
     * Sets a new word for the Hangman game.
     *
     * @param newWord The new word for the Hangman game.
     */
    public void setWord(String newWord) {
        currentWord = new Word(newWord);
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over (no attempts left), false otherwise.
     */
    public boolean isGameOver() {
        return attemptsLeft <= 0;
    }

    /**
     * Gets the current Word object in the Hangman game.
     *
     * @return The current Word object.
     */
    public Word getCurrentWord() {
        return currentWord;
    }

    /**
     * Gets the number of attempts left in the Hangman game.
     *
     * @return The number of attempts left.
     */
    public int getAttemptsLeft() {
        return attemptsLeft;
    }
}
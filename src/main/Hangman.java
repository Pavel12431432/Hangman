package main;

import java.util.Scanner;

public class Hangman {
    private Word currentWord;
    private int attemptsLeft = 10;

    public Hangman(String newWord) {
        setWord(newWord);
    }

    public String getGameState() {
        return currentWord.getMaskedWord() + "\t" + attemptsLeft + " attempts left";
    }

    public String concludeGame() {
        if (isGameOver()) {
            return "You lose! The word was: " + currentWord.getWord();
        } else {
            return "You win!";
        }
    }

    public String processGuess(char guessedLetter) {
        if (!currentWord.getWordLetters().contains(guessedLetter)) {
            attemptsLeft--;
            return "Incorrect guess.";
        }
        currentWord.revealLetter(guessedLetter);
        return "Good guess!";
    }

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

    public void playGame(Scanner sc) {
        System.out.println(getGameState());
        while (!currentWord.isWordGuessed() && !this.isGameOver()) {
            char guessedChar = promptForLetter(sc);
            System.out.println(processGuess(guessedChar));
            System.out.println(getGameState());
        }
        System.out.println(concludeGame());
    }

    public static char inputChar(String lineInput) {
        if (lineInput.length() != 1)
            throw new IllegalArgumentException("Input exactly 1 character");
        if ('a' > lineInput.charAt(0) || 'z' < lineInput.charAt(0))
            throw new IllegalArgumentException("Input a lowercase letter");
        return lineInput.charAt(0);
    }

    public void setWord(String newWord) {
        currentWord = new Word(newWord);
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0;
    }

    public Word getCurrentWord() {
        return currentWord;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }
}
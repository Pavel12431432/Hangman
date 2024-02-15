package main;

public class Hangman {
    private Word currentWord;
    private int attemptsLeft = 10;

    public Hangman() {

    }

    public Hangman(String newWord) {
        setWord(newWord);
    }

    public Hangman(Word newWord) {
        setWord(newWord);
    }

    // TODO: implement method startGame
    public void startGame() {

    }

    // TODO: implement method inputChar
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

    public void setWord(Word newWord) {
        currentWord = newWord;
    }

    public boolean isGameOver() {
        return attemptsLeft > 0;
    }

    public Word getCurrentWord() {
        return currentWord;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }
}
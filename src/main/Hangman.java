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
    private char inputChar() {
        return 'a';
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
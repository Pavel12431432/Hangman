package main;

public class Hangman {
    private Word currentWord;
    private int attemptsLeft;

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

    public void setWord(String newWord) {
        currentWord = new Word(newWord);
    }

    public void setWord(Word newWord) {
        currentWord = newWord;
    }

    public boolean isGameOver() {
        return attemptsLeft > 0;
    }
}
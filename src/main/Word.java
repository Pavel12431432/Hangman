package main;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String word;
    private List<Character> guessedLetters;

    public Word(String newWord) {
        word = newWord;
        guessedLetters = new ArrayList<>();
    }

    // TODO: implement method revealLetter
    public void revealLetter(char letter) {

    }

    // TODO: implement method getFormattedLetter
    public String getFormattedLetter() {
        return null;
    }

    // TODO: implement method isWordGuessed
    public boolean isWordGuessed() {
        return false;
    }
}

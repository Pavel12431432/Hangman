package main;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Word {
    private final String word;
    private final Set<Character> guessedLetters;
    private final Set<Character> wordLetters;

    public Word(String newWord) {
        word = newWord;
        wordLetters = newWord.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
        guessedLetters = new HashSet<>();
    }

    public void revealLetter(char letter) {
        guessedLetters.add(letter);
    }

    public String getMaskedWord() {
        StringBuilder maskedString = new StringBuilder(word.length());
        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(c)) {
                maskedString.append(c);
            } else {
                maskedString.append('_');
            }
        }
        return maskedString.toString();
    }

    public boolean isWordGuessed() {
        return guessedLetters.containsAll(wordLetters);
    }

    public String getWord() {
        return word;
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public Set<Character> getWordLetters() {
        return wordLetters;
    }
}

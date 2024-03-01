import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a word for the Hangman game.
 */
public class Word {
    private final String word;
    private final Set<Character> guessedLetters;
    private final Set<Character> wordLetters;

    /**
     * Constructs a Word object with the specified word.
     *
     * @param newWord The word to be represented in the Hangman game.
     */
    public Word(String newWord) {
        word = newWord;
        wordLetters = newWord.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
        guessedLetters = new HashSet<>();
    }

    /**
     * Reveals a letter in the word.
     *
     * @param letter The letter to be revealed.
     */
    public void revealLetter(char letter) {
        guessedLetters.add(letter);
    }

    /**
     * Returns the word with masked letters based on the guessed letters.
     *
     * @return The masked representation of the word.
     */
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

    /**
     * Checks if the entire word has been guessed.
     *
     * @return true if the word is completely guessed, false otherwise.
     */
    public boolean isWordGuessed() {
        return guessedLetters.containsAll(wordLetters);
    }

    /**
     * Gets the original word.
     *
     * @return The original word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets the set of guessed letters.
     *
     * @return The set of guessed letters.
     */
    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Gets the set of letters in the word.
     *
     * @return The set of letters in the word.
     */
    public Set<Character> getWordLetters() {
        return wordLetters;
    }
}

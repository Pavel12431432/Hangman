import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * JUnit tests for the {@link Word} class.
 */
public class WordTest {
    /**
     * Test the {@link Word#Word(String)} constructor.
     */
    @Test
    public void wordConstructorTest() {
        Word word = new Word("string");
        assert word.getWord().equals("string");
        assert word.getGuessedLetters().isEmpty();
    }

    /**
     * Test the {@link Word#revealLetter(char)} method in the {@link Word} class.
     */
    @Test
    public void givenWord_whenLettersRevealed_thenGuessedLettersSetMatchesExpected() {
        Word word = new Word("string");
        word.revealLetter('a');
        assert word.getGuessedLetters().equals(Set.of('a'));
        word.revealLetter('a');
        assert word.getGuessedLetters().equals(Set.of('a'));
        word.revealLetter('b');
        assert word.getGuessedLetters().equals(Set.of('a', 'b'));
    }

    /**
     * Test the {@link Word#revealLetter(char)} method in the {@link Word} class.
     */
    @Test
    public void givenWord_whenCorrectLettersRevealed_thenCorrectLettersAddedToGuessedSet() {
        Word word = new Word("string");
        word.revealLetter('s');
        assert word.getGuessedLetters().equals(Set.of('s'));
        word.revealLetter('t');
        assert word.getGuessedLetters().equals(Set.of('s', 't'));
        word.revealLetter('g');
        assert word.getGuessedLetters().equals(Set.of('s', 't', 'g'));
    }

    /**
     * Test the {@link Word#isWordGuessed()} method in the {@link Word} class.
     */
    @Test
    public void givenIncompleteWord_whenLettersRevealed_thenWordIsGuessedCorrectly() {
        Word word = new Word("string");
        assert !word.isWordGuessed();
        word.revealLetter('a');
        assert !word.isWordGuessed();
        word.revealLetter('i');
        assert !word.isWordGuessed();
        word.revealLetter('s');
        word.revealLetter('t');
        word.revealLetter('r');
        word.revealLetter('n');
        assert !word.isWordGuessed();
        word.revealLetter('g');
        assert word.isWordGuessed();
        word.revealLetter('o');
        assert word.isWordGuessed();
    }

    /**
     * Test the {@link Word#isWordGuessed()} method in the {@link Word} class.
     */
    @Test
    public void givenIncompleteWord_whenSomeLettersNotRevealed_thenWordIsNotGuessed() {
        Word word = new Word("example");
        word.revealLetter('e');
        word.revealLetter('x');
        assert !word.isWordGuessed();
    }

    /**
     * Test the {@link Word#getMaskedWord()} method in the {@link Word} class.
     */
    @Test
    public void givenWord_whenRevealingLetters_thenMaskedWordUpdatesAccordingly() {
        Word word = new Word("string");
        assert word.getMaskedWord().equals("______");
        word.revealLetter('a');
        assert word.getMaskedWord().equals("______");
        word.revealLetter('i');
        assert word.getMaskedWord().equals("___i__");
        word.revealLetter('g');
        assert word.getMaskedWord().equals("___i_g");
        word.revealLetter('z');
        assert word.getMaskedWord().equals("___i_g");
        word.revealLetter('s');
        assert word.getMaskedWord().equals("s__i_g");
        word.revealLetter('t');
        assert word.getMaskedWord().equals("st_i_g");
        word.revealLetter('n');
        assert word.getMaskedWord().equals("st_ing");
        word.revealLetter('r');
        assert word.getMaskedWord().equals("string");
        word.revealLetter('q');
        assert word.getMaskedWord().equals("string");
    }

    /**
     * Test the {@link Word#getMaskedWord()} method in the {@link Word} class.
     */
    @Test
    public void givenWord_whenGuessingAbsentLetters_thenMaskedWordRemainsUnchanged() {
        Word word = new Word("example");
        word.revealLetter('z');
        word.revealLetter('q');
        word.revealLetter('y');
        word.revealLetter('a');
        word.revealLetter('p');
        assert word.getMaskedWord().equals("_______");
    }
}

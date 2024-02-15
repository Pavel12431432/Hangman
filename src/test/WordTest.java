package test;

import main.Word;
import org.junit.jupiter.api.Test;

import java.util.*;

public class WordTest {
    @Test
    public void WordConstructorTest() {
        Word word = new Word("string");
        assert word.getWord().equals("string");
        assert word.getGuessedLetters().isEmpty();
    }

    @Test
    public void WordRevealLetterTest() {
        Word word = new Word("string");
        word.revealLetter('a');
        assert word.getGuessedLetters().equals(Set.of('a'));
        word.revealLetter('a');
        assert word.getGuessedLetters().equals(Set.of('a'));
        word.revealLetter('b');
        assert word.getGuessedLetters().equals(Set.of('a', 'b'));
    }

    @Test
    public void WordIsWordGuessedTest() {
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

    @Test
    public void WordGetMaskedLetterTest() {
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
}

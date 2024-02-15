package test;

import main.Hangman;
import main.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanTest {
    @Test
    public void HangmanDefaultConstructorTest() {
        Hangman hangman = new Hangman();
        assert hangman.getCurrentWord() == null;
        assert hangman.getAttemptsLeft() == 10;
    }

    @Test
    public void HangmanStringConstructorTest() {
        String word = "string";
        Hangman hangman = new Hangman(word);
        assert word.equals(hangman.getCurrentWord().getWord());
        assert hangman.getAttemptsLeft() == 10;
    }

    @Test
    public void HangmanWordConstructorTest() {
        Word word = new Word("example");
        Hangman hangman = new Hangman(word);
        assert word.equals(hangman.getCurrentWord());
        assert hangman.getAttemptsLeft() == 10;
    }

    @Test
    public void HangmanInputCharTest() {
        assert Hangman.inputChar("a") == 'a';
        assert Hangman.inputChar("z") == 'z';
        // check if these raise exceptions
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("abc"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("Z"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar(" "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("aa aaa aa"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("1"));
    }


}

package test;

import main.Hangman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HangmanTest {
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

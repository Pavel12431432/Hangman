package test;

import main.Hangman;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class HangmanTest {
    private Hangman hangman;
    @BeforeEach
    public void setUp() {
        hangman = new Hangman("string");
    }

    @Test
    public void HangmanConstructorTest() {
        assert hangman.getCurrentWord().getWord().equals("string");
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

    @Test
    public void HangmanGetGameStateTest() {
        assert hangman.getGameState().equals("______\t10 attempts left");
        hangman.processGuess('t');
        assert hangman.getGameState().equals("_t____\t10 attempts left");
        hangman.processGuess('q');
        assert hangman.getGameState().equals("_t____\t9 attempts left");
        hangman.processGuess('i');
        assert hangman.getGameState().equals("_t_i__\t9 attempts left");
        hangman.processGuess('i');
        assert hangman.getGameState().equals("_t_i__\t9 attempts left");
        hangman.processGuess('p');
        assert hangman.getGameState().equals("_t_i__\t8 attempts left");
    }

    @Test
    public void HangmanConcludeGameTest() {
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('s');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('q');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('t');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('y');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('u');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('i');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('p');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('l');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('k');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('j');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('h');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('f');
        assert hangman.concludeGame().equals("You win!");
        hangman.processGuess('z');
        assert hangman.concludeGame().equals("You lose! The word was: string");
        hangman.processGuess('s');
        assert hangman.concludeGame().equals("You lose! The word was: string");
    }

    @Test
    public void HangmanProcessGuessTest() {
        assert hangman.processGuess('z').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 9;
        assert hangman.processGuess('r').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 9;
        assert hangman.processGuess('q').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 8;
        assert hangman.processGuess('i').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 8;
        assert hangman.processGuess('b').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 7;
    }

    @Test
    public void HangmanPromptForLetterTest() {
        String input = "a\n5\nk\nabc\nasd\n)\np";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assert 'a' == hangman.promptForLetter(scanner);
        assert 'k' == hangman.promptForLetter(scanner);
        assert 'p' == hangman.promptForLetter(scanner);
    }

    @Test
    public void HangmanPlayGameTest() {
        String input = "s\nt\nr\np\ni\nn\ng\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        hangman.playGame(scanner);
        assert hangman.getAttemptsLeft() == 9;
        assert !hangman.isGameOver();
        assert hangman.getCurrentWord().getMaskedWord().equals("string");
        assert hangman.concludeGame().equals("You win!");
    }

    @AfterAll
    public static void resetSystemIn() {
        System.setIn(System.in);
    }
}

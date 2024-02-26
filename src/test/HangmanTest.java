package test;

import main.input.ConsoleInput;
import main.Hangman;
import main.input.InputReader;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;

/**
 * JUnit tests for the {@link Hangman} class.
 */
public class HangmanTest {
    private Hangman hangman;
    /**
     * Set up the Hangman object before each test.
     */
    @BeforeEach
    public void setUp() {
        hangman = new Hangman("string");
    }

    /**
     * Test the {@link Hangman#Hangman(String)} constructor.
     */
    @Test
    public void hangmanConstructorTest() {
        assert hangman.getCurrentWord().getWord().equals("string");
        assert hangman.getAttemptsLeft() == 10;
    }

    /**
     * Test the {@link Hangman#inputChar(String)} method of the {@link Hangman} class.
     */
    @Test
    public void hangmanInputCharTest() {
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

    /**
     * Test the {@link Hangman#getGameState()} method of the {@link Hangman} class.
     */
    @Test
    public void hangmanGetGameStateTest() {
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

    /**
     * Test the {@link Hangman#concludeGame()} method of the {@link Hangman} class.
     */
    @Test
    public void hangmanConcludeGameTest() {
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

    /**
     * Test the {@link Hangman#processGuess(char)} method of the {@link Hangman} class.
     */
    @Test
    public void hangmanProcessGuessTest() {
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

    /**
     * Test the {@link Hangman#promptForLetter(Scanner)} method of the {@link Hangman} class.
     */
    @Test
    public void hangmanPromptForLetterTest() {
        String input = "a\n5\nk\nabc\nasd\n)\np";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputReader inputTest = new ConsoleInput();

        assert 'a' == hangman.promptForLetter(inputTest);
        assert 'k' == hangman.promptForLetter(inputTest);
        assert 'p' == hangman.promptForLetter(inputTest);
    }

    /**
     * Test the {@link Hangman#playGame(Scanner)} method of the {@link Hangman} class.
     */
    @Test
    public void hangmanPlayGameTest() {
        String input = "s\nt\nr\np\ni\nn\ng\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputReader inputTest = new ConsoleInput();

        hangman.playGame(inputTest);
        assert hangman.getAttemptsLeft() == 9;
        assert !hangman.isGameOver();
        assert hangman.getCurrentWord().getMaskedWord().equals("string");
        assert hangman.concludeGame().equals("You win!");
    }

    /**
     * Reset System.in after all tests have been executed.
     */
    @AfterAll
    public static void resetSystemIn() {
        System.setIn(System.in);
    }
}

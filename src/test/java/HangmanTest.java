import input.ConsoleInput;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import output.Output;
import output.OutputConsole;
import input.InputReader;

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
        hangman = new Hangman("string", 10);
    }

    /**
     * Test the {@link Hangman#Hangman(String, int)} constructor.
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
    public void givenValidOrInvalidInput_whenInputCharIsCalled_thenCorrectCharIsReturnedOrExceptionIsThrown() {
        assert Hangman.inputChar("a") == 'a';
        assert Hangman.inputChar("z") == 'z';
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("abc"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("Z"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar(" "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("aa aaa aa"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("1"));
    }

    /**
     * Test the {@link Hangman#inputChar(String)} method of the {@link Hangman} class.
     */
    @Test
    public void givenEdgeCaseInputs_whenInputCharIsCalled_thenIllegalArgumentExceptionIsThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("A"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("G"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("1"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("9"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("!"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("@"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Hangman.inputChar("#"));
    }

    /**
     * Test the {@link Hangman#getGameState()} method of the {@link Hangman} class.
     */
    @Test
    public void givenSequenceOfGuessesLeadingToWin_whenProcessGuessAndUpdateGameState_thenWinConditionIsReflectedInGameState() {
        assert hangman.getGameState().equals("______\t10 attempts left");

        hangman.processGuess('c');
        assert hangman.getGameState().equals("c_____\t10 attempts left");
        hangman.processGuess('o');
        assert hangman.getGameState().equals("co___\t10 attempts left");
        hangman.processGuess('d');
        assert hangman.getGameState().equals("cod__\t10 attempts left");
        hangman.processGuess('i');
        assert hangman.getGameState().equals("codi_\t10 attempts left");
        hangman.processGuess('n');
        assert hangman.getGameState().equals("codin\t10 attempts left");
        hangman.processGuess('g');

        assert hangman.getGameState().equals("coding\tYou won!");
    }

    /**
     * Test the {@link Hangman#getGameState()} method of the {@link Hangman} class.
     */
    @Test
    public void givenIncorrectGuessesDepletingAttempts_whenProcessGuessAndUpdateGameState_thenLossConditionIsReflectedInGameState() {
        assert hangman.getGameState().equals("______\t10 attempts left");

        hangman.processGuess('q');
        assert hangman.getGameState().equals("______\t9 attempts left");
        hangman.processGuess('w');
        assert hangman.getGameState().equals("______\t8 attempts left");
        hangman.processGuess('e');
        assert hangman.getGameState().equals("______\t7 attempts left");
        hangman.processGuess('r');
        assert hangman.getGameState().equals("______\t6 attempts left");
        hangman.processGuess('t');
        assert hangman.getGameState().equals("______\t5 attempts left");
        hangman.processGuess('y');
        assert hangman.getGameState().equals("______\t4 attempts left");
        hangman.processGuess('u');
        assert hangman.getGameState().equals("______\t3 attempts left");
        hangman.processGuess('i');
        assert hangman.getGameState().equals("______\t2 attempts left");
        hangman.processGuess('o');
        assert hangman.getGameState().equals("______\t1 attempt left");
        hangman.processGuess('p');

        assert hangman.getGameState().equals("______\tYou lost! The word was: [actual word]");
    }

    /**
     * Test the {@link Hangman#concludeGame()} method of the {@link Hangman} class.
     */
    @Test
    public void givenMultipleGuesses_whenConcludeGameCalled_thenWinMessageConsistentlyReturned() {
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
    }

    /**
     * Test the {@link Hangman#concludeGame()} method of the {@link Hangman} class.
     */
    @Test
    public void givenIncorrectGuessesExceedingLimit_whenConcludeGameCalled_thenLoseMessageReturned() {
        hangman.processGuess('a');
        hangman.processGuess('b');
        hangman.processGuess('c');
        hangman.processGuess('d');
        hangman.processGuess('e');
        hangman.processGuess('f');
        hangman.processGuess('g');
        hangman.processGuess('h');
        hangman.processGuess('y');
        hangman.processGuess('z');
        assert hangman.concludeGame().equals("You lose! The word was: [actual word]");
        hangman.processGuess('s');
        assert hangman.concludeGame().equals("You lose! The word was: [actual word]");
    }


    /**
     * Test the {@link Hangman#processGuess(char)} method of the {@link Hangman} class.
     */
    @Test
    public void givenCorrectGuesses_whenProcessGuessIsCalled_thenPositiveFeedbackIsGivenAndAttemptsRemainUnchanged() {
        assert hangman.processGuess('z').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 10;
        assert hangman.processGuess('r').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 10;
        assert hangman.processGuess('q').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 10;
        assert hangman.processGuess('i').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 10;
        assert hangman.processGuess('b').equals("Good guess!");
        assert hangman.getAttemptsLeft() == 10;
    }

    /**
     * Test the {@link Hangman#processGuess(char)} method of the {@link Hangman} class.
     */
    @Test
    public void givenIncorrectGuesses_whenProcessGuessIsCalled_theNegativeFeedbackIsGivenAndAttemptsChange() {
        assert hangman.processGuess('z').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 9;
        assert hangman.processGuess('r').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 8;
        assert hangman.processGuess('q').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 7;
        assert hangman.processGuess('i').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 6;
        assert hangman.processGuess('b').equals("Incorrect guess.");
        assert hangman.getAttemptsLeft() == 5;
    }

    /**
     * Test the {@link Hangman#promptForLetter(InputReader, Output)} method of the {@link Hangman} class.
     */
    @Test
    public void givenOnlyValidInputs_whenPromptForLetterIsCalled_thenCorrectlyReturnsEachLetter() {
        String input = "a\ns\nd\nf\ng\nh\nj";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputReader inputTest = new ConsoleInput();
        Output o = new OutputConsole();

        assert 'a' == hangman.promptForLetter(inputTest, o);
        assert 's' == hangman.promptForLetter(inputTest, o);
        assert 'd' == hangman.promptForLetter(inputTest, o);

        System.setIn(System.in);
    }

    /**
     * Test the {@link Hangman#promptForLetter(InputReader, Output)} method of the {@link Hangman} class.
     */
    @Test
    public void givenInvalidInputsFollowedByValid_whenPromptForLetterIsCalled_thenIgnoresInvalidAndReturnsValidLetter() {
        String input = "123\n!!\nabc\nZ\n.\n7\nm";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputReader inputTest = new ConsoleInput();
        Output o = new OutputConsole();

        assert 'm' == hangman.promptForLetter(inputTest, o);

        System.setIn(System.in);
    }


    /**
     * Test the {@link Hangman#playGame(InputReader, Output)} method of the {@link Hangman} class.
     */
    @Test
    public void givenHangmanGame_whenPlayingGameWithValidInput_thenGameContinuesAndPlayerWins() {
        String input = "s\nt\nr\np\ni\nn\ng\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputReader inputTest = new ConsoleInput();
        Output o = new OutputConsole();

        hangman.playGame(inputTest, o);

        assert hangman.getAttemptsLeft() == 9;
        assert !hangman.isGameOver();
        assert hangman.getCurrentWord().getMaskedWord().equals("string");
        assert hangman.concludeGame().equals("You win!");
    }

    /**
     * Test the {@link Hangman#playGame(InputReader, Output)} method of the {@link Hangman} class.
     */
    @Test
    public void givenHangmanGame_whenPlayingGameWithInvalidInput_thenGameEndsWithLoss() {
        String invalidInput = "1\n@\n#\n$\n%\n^\n&\n*\n";
        System.setIn(new ByteArrayInputStream(invalidInput.getBytes()));
        InputReader inputTest = new ConsoleInput();
        Output o = new OutputConsole();

        hangman.playGame(inputTest, o);

        assert hangman.getAttemptsLeft() == 0;
        assert !hangman.isGameOver();
        assert hangman.concludeGame().equals("You lose!");
    }

    /**
     * Reset System.in after all tests have been executed.
     */
    @AfterAll
    public static void resetSystemIn() {
        System.setIn(System.in);
    }
}
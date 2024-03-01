import output.Output;
import input.InputReader;

/**
 * The Hangman class represents a Hangman game.
 */
public class Hangman {
    private Word currentWord;
    private int attemptsLeft = 10;

    private static final String attemptsLeftText = " attempts left";
    private static final String loseText = "You lose! The word was: ";
    private static final String winText = "You win!";
    private static final String incorrectGuessText = "Incorrect guess.";
    private static final String correctGuessText = "Good guess!";
    private static final String alreadyGuessedLetterText = "Letter is already guessed.";
    private static final String inputOneLetterText = "Input exactly 1 character";
    private static final String inputLowercaseLetterText = "Input a lowercase letter";
    /**
     * Constructs a Hangman game with the specified word.
     *
     * @param newWord The word for the Hangman game.
     */
    public Hangman(String newWord) {
        setWord(newWord);
    }

    /**
     * Gets the current state of the game, including the masked word and attempts left.
     *
     * @return The current game state as a formatted string.
     */
    public String getGameState() {
        return String.join("", currentWord.getMaskedWord(), "\t", Integer.toString(attemptsLeft), attemptsLeftText);
    }

    /**
     * Concludes the game and returns the result message.
     *
     * @return The result message indicating whether the player won or lost.
     */
    public String concludeGame() {
        if (isGameOver()) {
            return loseText + currentWord.getWord();
        } else {
            return winText;
        }
    }

    /**
     * Processes a guessed letter and updates the game state.
     *
     * @param guessedLetter The letter guessed by the player.
     * @return A message indicating whether the guess was correct or incorrect.
     */
    public String processGuess(char guessedLetter) {
        if (!currentWord.getWordLetters().contains(guessedLetter)) {
            attemptsLeft--;
            return incorrectGuessText;
        }
        currentWord.revealLetter(guessedLetter);
        return correctGuessText;
    }

    /**
     * Prompts the user for a letter input.
     *
     * @param input The InputReader object for input.
     * @return The guessed letter provided by the user.
     */
    public char promptForLetter(InputReader input, Output out) {
        char guessedLetter;
        while (true) {
            try {
                guessedLetter = inputChar(input.readLine());
                if (currentWord.getGuessedLetters().contains(guessedLetter)) {
                    out.outputLine(alreadyGuessedLetterText);
                } else {
                    return guessedLetter;
                }
            } catch (IllegalArgumentException e) {
                out.outputLine(e.getMessage());
            }
        }
    }

    /**
     * Plays the Hangman game until the word is guessed or the player runs out of attempts.
     *
     * @param input The InputReader object for input.
     */
    public void playGame(InputReader input, Output out) {
        out.outputLine(getGameState());
        while (!currentWord.isWordGuessed() && !this.isGameOver()) {
            char guessedChar = promptForLetter(input, out);
            out.outputLine(processGuess(guessedChar));
            out.outputLine(getGameState());
        }
        out.outputLine(concludeGame());
    }

    /**
     * Converts a string input to a character and performs validation.
     *
     * @param lineInput The input string to convert to a character.
     * @return The converted character.
     * @throws IllegalArgumentException If the input is not a single lowercase letter.
     */
    public static char inputChar(String lineInput) {
        if (lineInput.length() != 1)
            throw new IllegalArgumentException(inputOneLetterText);
        if ('a' > lineInput.charAt(0) || 'z' < lineInput.charAt(0))
            throw new IllegalArgumentException(inputLowercaseLetterText);
        return lineInput.charAt(0);
    }

    /**
     * Sets a new word for the Hangman game.
     *
     * @param newWord The new word for the Hangman game.
     */
    public void setWord(String newWord) {
        currentWord = new Word(newWord);
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over (no attempts left), false otherwise.
     */
    public boolean isGameOver() {
        return attemptsLeft <= 0;
    }

    /**
     * Gets the current Word object in the Hangman game.
     *
     * @return The current Word object.
     */
    public Word getCurrentWord() {
        return currentWord;
    }

    /**
     * Gets the number of attempts left in the Hangman game.
     *
     * @return The number of attempts left.
     */
    public int getAttemptsLeft() {
        return attemptsLeft;
    }
}
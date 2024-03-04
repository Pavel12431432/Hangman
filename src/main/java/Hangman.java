import output.Output;
import input.InputReader;

/**
 * The Hangman class represents a Hangman game.
 */
public class Hangman {
    private Word currentWord;
    private int attemptsLeft;

    private static final String attemptsLeftText = " attempts left";
    private static final String loseText = "You lose! The word was: ";
    private static final String winText = "You win!";
    private static final String incorrectGuessText = "Incorrect guess.";
    private static final String correctGuessText = "Good guess!";
    private static final String alreadyGuessedLetterText = "Letter is already guessed.";
    private static final String inputOneLetterText = "Input exactly 1 character";
    private static final String inputLetterText = "Input only letters";
    /**
     * Constructs a Hangman game with the specified word.
     *
     * @param newWord The word for the Hangman game.
     * @param totalAttempts Total number of attempts to guess word.
     */
    public Hangman(String newWord, int totalAttempts) {
        setWord(newWord);
        attemptsLeft = totalAttempts;
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
        char guessedLetterToUpper = Character.toUpperCase(guessedLetter);
        char guessedLetterToLower = Character.toLowerCase(guessedLetter);
        if (!currentWord.getWordLetters().contains(guessedLetterToUpper) && !currentWord.getWordLetters().contains(guessedLetterToLower)) {
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
                char guessedLetterToUpper = Character.toUpperCase(guessedLetter);
                char guessedLetterToLower = Character.toLowerCase(guessedLetter);
                if (currentWord.getGuessedLetters().contains(guessedLetterToUpper) || currentWord.getGuessedLetters().contains(guessedLetterToLower)) {
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
     * Checks if a given string contains only lowercase letters.
     * <p>
     * This method uses a regular expression to determine if the input string consists exclusively of lowercase letters from 'a' to 'z'. The input is considered valid if it contains one or more lowercase letters and nothing else.
     * </p>
     *
     * @param input The string to be checked.
     * @return {@code true} if the input string contains only lowercase letters; {@code false} otherwise.
     */
    public static boolean containsOnlyLetters(String input) {
        // Define the regex pattern for a string of only lowercase letters
        String pattern = "^[A-Za-z]+$";

        // Check if the input matches the pattern
        return input.matches(pattern);
    }

    /**
     * Checks if the input contains more than two symbols (non-alphanumeric characters).
     *
     * @param input the string to check
     * @return true if the input contains more than two symbols, false otherwise
     */
    public static boolean containsOnlyOneSymbol(String input) {
        // Define the regex pattern for identifying more than two non-alphanumeric characters
        String pattern = "^[a-zA-Z0-9]$";

        // Check if the input matches the pattern
        return input.matches(pattern);
    }
    /**
     * Extracts and returns the first character from a given string after validating that it contains exactly one lowercase letter.
     * <p>
     * This method first checks if the provided string contains only lowercase letters using the {@code containsOnlyLowercaseLetters} method. If the string contains any character other than a lowercase letter, an {@code IllegalArgumentException} is thrown with a specific message. It then checks if the string contains exactly one symbol using the {@code containsOnlyOneSymbol} method. If the string contains more than one symbol, another {@code IllegalArgumentException} is thrown with a different specific message. After passing these validations, the method returns the first character of the string.
     * </p>
     *
     * @param lineInput The input string to be processed.
     * @return The first character of the input string if it passes the validation checks.
     * @throws IllegalArgumentException If the input string does not contain only lowercase letters or if it does not contain exactly one symbol.
     */
    public static char inputChar(String lineInput) {
        if(!containsOnlyLetters(lineInput)){
            throw new IllegalArgumentException(inputLetterText);
        }
        if(!containsOnlyOneSymbol(lineInput)){
            throw new IllegalArgumentException(inputOneLetterText);
        }
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
import java.util.Scanner;
import java.util.Random;

public class HangmanGame {
    private static final String[] WORDS = {"apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "lemon", "mango", "orange"};
    private static final int MAX_TRIES = 6;

    private String secretWord;
    private static StringBuilder currentWord;
    private int incorrectGuesses;

    public HangmanGame() {
        // Initialize the game
        Random random = new Random();
        secretWord = WORDS[random.nextInt(WORDS.length)];
        currentWord = new StringBuilder("_".repeat(secretWord.length()));
        incorrectGuesses = 0;
    }

    public boolean isGameOver() {
        return incorrectGuesses >= MAX_TRIES || currentWord.indexOf("_") == -1;
    }

    public void displayGame() {
        System.out.println("Welcome to the Hangman Game");
        System.out.println("Current word: " + currentWord.toString());
        System.out.println("Incorrect guesses: " + incorrectGuesses + "/" + MAX_TRIES);
    }

    public void guessLetter(char letter) {
        boolean correctGuess = false;

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                currentWord.setCharAt(i, letter);
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            incorrectGuesses++;
        }
    }

    public static void main(String[] args) {
        int again = 1;
        for (int index = 0; index < again; index = index + 1) {
            Scanner scanner = new Scanner(System.in);
            HangmanGame hangman = new HangmanGame();

            while (!hangman.isGameOver()) {
                hangman.displayGame();
                System.out.print("Guess a letter: ");
                char guess = scanner.next().charAt(0);
                hangman.guessLetter(guess);
            }

            if (currentWord.indexOf("_") == -1) {
                System.out.println("Congratulations! You've guessed the word correctly! the word was : " + hangman.secretWord);
            } else {
                System.out.println("Sorry, you've run out of tries. The word was: " + hangman.secretWord);
            }
            System.out.println("Do you want to play again? (yes / no):");
            String repeat = scanner.next();
            if (repeat.equals("yes")) {
                again = again + 1;
            }
            else {
                System.out.println("Thank you for playing HangmanGame!");
            }
        }
    }
}

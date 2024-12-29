import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hangman_Game {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        intro();

        int gamesWon = 0;
        int gamesCount = 0;
        int best = 0;

        while (true) {
            String secretWord = getRandomWord("dict.txt", input);
            int guesses_left = playOneGame(secretWord, input);

            if (guesses_left > 0) {
                System.out.println("You win! My word was \"" + secretWord + "\".");
                gamesWon++;
                gamesCount++;
            } else {
                System.out.println("Game over! My word was \"" + secretWord + "\".");
                gamesCount++;
            }

            if (best < guesses_left) {
                best = guesses_left;
            }

            System.out.println("Play again (Y/N)?");
            while (true) {
                String playagain = input.next().toUpperCase();
                if (playagain.equals("N")) {
                    stats(gamesCount, gamesWon, best);
                    System.out.println("Thank you for playing.");

                } else if (playagain.equals("Y")) {
                    break;
                } else {
                    System.out.println("Invalid input. Do you want to play again(Y/N):  ");

                }

            }
            break;
        }
    }

    private static void intro() {
        System.out.println("Hangman Game!\n"
                + "I will think of a random word.\n"
                + "You'll try to guess its letters.\n"
                + "Every time you guess a letter\n"
                + "that isn't in my word, a new body part\n"
                + " of the hanging man appears.\n"
                + "Guess correctly to avoid the gallows!");

    }

    private static void displayHangman(int num_attempts) throws FileNotFoundException {

        if (num_attempts == 8) {
            File A = new File("display8.txt");
            Scanner D8 = new Scanner(A);
            while (D8.hasNextLine()) {
                String display8 = D8.nextLine();
                System.out.println(display8);
            }

        }
        if (num_attempts == 7) {
            File B = new File("display7.txt");
            Scanner D7 = new Scanner(B);
            while (D7.hasNextLine()) {
                String display7 = D7.nextLine();
                System.out.println(display7);
            }

        }
        if (num_attempts == 6) {
            File C = new File("display6.txt");
            Scanner D6 = new Scanner(C);
            while (D6.hasNextLine()) {
                String display6 = D6.nextLine();
                System.out.println(display6);
            }

        }
        if (num_attempts == 5) {
            File d = new File("display5.txt");
            Scanner D5 = new Scanner(d);
            while (D5.hasNextLine()) {
                String display5 = D5.nextLine();
                System.out.println(display5);
            }
        }
        if (num_attempts == 4) {
            File E = new File("display4.txt");
            Scanner D4 = new Scanner(E);
            while (D4.hasNextLine()) {
                String display4 = D4.nextLine();
                System.out.println(display4);
            }

        }
        if (num_attempts == 3) {

            File F = new File("display3.txt");
            Scanner D3 = new Scanner(F);
            while (D3.hasNextLine()) {
                String display3 = D3.nextLine();
                System.out.println(display3);
            }
        }
        if (num_attempts == 2) {
            File G = new File("display2.txt");
            Scanner D2 = new Scanner(G);
            while (D2.hasNextLine()) {
                String display2 = D2.nextLine();
                System.out.println(display2);
            }

        }
        if (num_attempts == 1) {
            File H = new File("display1.txt");
            Scanner D1 = new Scanner(H);
            while (D1.hasNextLine()) {
                String display1 = D1.nextLine();
                System.out.println(display1);
            }
        }
    }

    private static int playOneGame(String secretWord, Scanner input) throws FileNotFoundException {

        int num_attempts = 8;
        String guessedLetters = "";
        String guessedWord = createHint(secretWord, guessedLetters);
        boolean guessCorrect;

        if (num_attempts == 8)
            displayHangman(8);
        while (num_attempts > 0) {
            System.out.println("Secret word: " + guessedWord);
            System.out.println("Your guesses: " + guessedLetters);
            System.out.println("Guesses left: " + num_attempts);
            System.out.print("Your guess? ");

            char userGuess = readGuess(guessedLetters, input);

            guessedLetters += userGuess;
            guessCorrect = false;

            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == userGuess) {
                    guessedWord = guessedWord.substring(0, i) + userGuess + guessedWord.substring(i + 1);
                    guessCorrect = true;
                }
            }

            if (guessCorrect) {
                System.out.println("Correct!");

            } else {
                System.out.println("Incorrect!");
                num_attempts--;
            }

            if (guessedWord.equals(secretWord)) {
                System.out.println("Secret word: " + secretWord);
                return num_attempts;
            }

            for (int i = 0; i <= 8; i++) {

                if (i == num_attempts)
                    displayHangman(i);
            }
        }

        return num_attempts;
    }

    private static String createHint(String secretWord, String guessedLetters) {
        String dashes = "";

        for (int i = 0; i < secretWord.length(); i++) {
            char letter = secretWord.charAt(i);
            if (guessedLetters.contains(String.valueOf(letter))) {
                dashes += letter;
            } else {
                dashes += '-';
            }
        }
        return dashes;
    }

    private static char readGuess(String guessedLetters, Scanner input) {

        while (true) {
            // System.out.print("Your guess? ");
            String userString = input.next().toUpperCase();

            if (userString.length() != 1) {
                System.out.println("Type a single letter from A-Z.");
                System.out.print("Your guess? ");
                continue;
            }

            char userGuess = userString.charAt(0);

            if (!Character.isLetter(userGuess) || userGuess < 'A' || userGuess > 'Z') {
                System.out.println("Type a single letter from A-Z.");
                System.out.print("Your guess? ");
                continue;
            } else if (guessedLetters.contains(String.valueOf(userGuess))) {
                System.out.println("You already guessed that letter.");
                System.out.print("Your guess? ");
                continue;
            } else {
                return userGuess;
            }
        }

    }

    private static String getRandomWord(String filename, Scanner input) throws FileNotFoundException {

        input = new Scanner(new File("dict.txt"));

        while (input.hasNextInt()) {

            if (!input.hasNextInt()) {
                input.next();
            } else {
                int random = (int) (Math.random() * 74) + 1;
                int l = 1;

                while (l < random) {
                    input.nextLine();
                    l++;
                }

            }

        }
        return input.nextLine();

    }

    private static void stats(int gamesCount, int gamesWon, int best) {

        System.out.println("Overall statistics: ");
        System.out.println("Games played = " + gamesCount);
        System.out.println("Games won = " + gamesWon);

        double winPercentage = (double) gamesWon / gamesCount * 100.0;

        System.out.printf("Win percent: %.1f%%\n", winPercentage);

        System.out.println("Best game: " + best + " guess(es) remaining");
    }
}

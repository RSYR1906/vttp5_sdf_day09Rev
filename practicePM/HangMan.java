package practicePM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangMan {

    public static void main(String[] args) throws IOException {

        // Create a scanner to be used for both guessing and replay prompts
        Scanner scanner = new Scanner(System.in);

        // Outer loop to handle starting a new game
        while (true) {
            playHangManGame(scanner);

            // Prompt to start a new game or quit
            System.out.print("\nDo you want to start a new game? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();

            if (!response.equals("yes")) {
                System.out.println("Thanks for playing! Goodbye!");
                break; // Exit the game loop
            }
        }
    }

    public static void playHangManGame(Scanner scanner) throws IOException {
        List<String> guessCharBoard = new ArrayList<>();
        List<String> wordPickBoard = new ArrayList<>();
        List<String> guessHistory = new ArrayList<>();

        // read a list of words from a file (FileReader)
        List<String> wordList = readFromFile("wordList.txt");

        // randomly pick a word to guess (Random package)
        String wordPicked = generateRandomWord(wordList);

        // create blanks for word guess board
        guessCharBoard = generateGuessBoard(wordPicked, guessCharBoard);
        // board for word picked
        wordPickBoard = generateWordPickedBoard(wordPicked, wordPickBoard);

        int wrongCounter = 0;
        boolean isGameOver = false;

        while (!isGameOver) {

            // Create scanner object and take in input (characters)
            System.out.println();
            System.out.print("Make a guess by entering a character: ");
            scanner = new Scanner(System.in);

            String guessedCharacterStr = scanner.next();
            boolean isCorrectGuess = false;

            // Iterate through the word and find guessed character. If can find the
            // character, get the index and add to the guessCharBoard
            for (int k = 0; k < wordPickBoard.size(); k++) {
                if (wordPickBoard.get(k).equals(guessedCharacterStr)) { // take into account same character in
                                                                        // different position
                    guessCharBoard.set(k, guessedCharacterStr);
                    isCorrectGuess = true;
                }
            }
            // If guess incorrect, add to counter to print hangman
            if (!isCorrectGuess) {
                wrongCounter++;
                guessHistory.add(guessedCharacterStr);
            }

            System.out.println("Wrong guesses: " + guessHistory + "\n");
            System.out.println(guessCharBoard + "\n");

            // If cannot find, draw a stroke in the hangman
            if (wrongCounter == 4) {
                System.out.println(" _____");
                System.out.println("|     |");
                System.out.println("|     |");
                System.out.println("|     |");
                System.out.println("|     |");
                System.out.println(" _____");
                System.out.println("Guesses left: 7");
            }

            if (wrongCounter == 9) {
                System.out.println(" _____");
                System.out.println("|  |  |");
                System.out.println("|  O  |");
                System.out.println("|  |  |");
                System.out.println("| / \\ |");
                System.out.println(" _____");
                System.out.println("Guesses left: 2");
            }

            if (wrongCounter == 11) {
                System.out.println(" _____");
                System.out.println("|  |  |");
                System.out.println("|  O  |");
                System.out.println("| -|-  |");
                System.out.println("| / \\ |");
                System.out.println(" _____");
                System.out.println("You lose!");
                isGameOver = true;
            }

            // Check if word is guessed completely
            if (guessCharBoard.contains("_") == false) {
                System.out.println("\nYou guessed the word!");
                isGameOver = true;
            }

        }
    }

    public static List<String> readFromFile(String fileName) throws IOException {
        List<String> wordList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("wordList.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            wordList.add(line);
        }
        br.close();
        return wordList;
    }

    public static String generateRandomWord(List<String> wordList) {
        Random rand = new Random();
        int wordPickedIndex = rand.nextInt(wordList.size());
        String wordPicked = wordList.get(wordPickedIndex);
        return wordPicked;
    }

    public static List<String> generateGuessBoard(String word, List<String> board) {
        for (int i = 0; i < word.length(); i++) {
            board.add("_");
        }
        return board;
    }

    public static List<String> generateWordPickedBoard(String word, List<String> board) {
        for (int j = 0; j < word.length(); j++) {
            char letterChar = word.charAt(j);
            String letterStr = Character.toString(letterChar);
            board.add(letterStr);
        }
        return board;
    }
}

package practiceAM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGameTask9 {

    public static void main(String[] args) throws IOException {

        Random rand = new Random();
        int generatedNumber = rand.nextInt(111111, 999999);
        System.out.println(generatedNumber);

        int guessNumber = 0;

        File guessHistory = new File("guessHistory.txt");

        System.out.println("Welcome to the Guessing Game!");
        System.out.println("==============================");
        System.out.print("Guess a six digit number between 111111 and 999999: ");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String guessNumberstr = scanner.nextLine();
            guessNumber = Integer.parseInt(guessNumberstr);
            BufferedWriter bw = new BufferedWriter(new FileWriter(guessHistory, true));
            bw.write(guessNumberstr);
            bw.newLine();
            bw.flush();

            if (guessNumber < 111111 || guessNumber > 999999) {
                System.out.println("Number guessed should be in the range of 111111 and 999999");
                System.out.print("Guess again: ");

            }

            else if (guessNumber < generatedNumber) {
                System.out.println("Hint: Number is higher");
                System.out.print("Guess again: ");
            }

            else if (guessNumber > generatedNumber) {
                System.out.println("Hint: Number is lower");
                System.out.print("Guess again: ");

            }

            else if (guessNumber == generatedNumber) {
                System.out.println("You guessed it!");
                break;

            }

            bw.close();
        }
        scanner.close();

    }
}
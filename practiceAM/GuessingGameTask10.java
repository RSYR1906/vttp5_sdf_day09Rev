package practiceAM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuessingGameTask10 {

    public static void main(String[] args) throws IOException {

        List<Integer> numberList = new ArrayList<>();
        List<String> guessedList = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int generatedNumber = rand.nextInt(101);
            numberList.add(generatedNumber);
        }
        // System.out.println(numberList);
        Collections.shuffle(numberList);
        // System.out.println(numberList);

        int currentNumber = numberList.get(0);
        System.out.println("First number: " + currentNumber);
        System.out.print("Guess whether the next number is higher or lower: ");

        Scanner scan = new Scanner(System.in);

        for (int i = 1; i < numberList.size(); i++) {
            String guess = scan.nextLine();
            guessedList.add(guess);

            int nextNumber = numberList.get(i);

            if (currentNumber > nextNumber && guess.equals("lower")) {
                System.out.println("\nYou guessed correctly! Next Number is " + nextNumber);

            } else if (currentNumber < nextNumber && guess.equals("higher")) {
                System.out.println("\nYou guessed correctly! Next Number is " + nextNumber);

            } else {
                System.out.println("\nYou guessed wrongly! Next Number is " + nextNumber);

            }
            numberList.remove(Integer.valueOf(currentNumber));

            int counterHigher = 0;
            int counterLower = 0;
            for (int number : numberList) {
                if (number > currentNumber) {
                    counterHigher++;
                } else {
                    counterLower++;
                }
            }

            System.out.println("Numbers higher than current number: " + counterHigher);
            System.out.println("Numbers lower than current number: " + counterLower);

            currentNumber = nextNumber;

            if (i < numberList.size() - 1) {
                System.out.print("\nGuess higher or lower for the next number: ");
            }
        }
        scan.close();
    }
}

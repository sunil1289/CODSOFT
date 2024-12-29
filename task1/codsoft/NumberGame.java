package com.task1.codsoft;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean PlayAgain = true;
        int TotalScore = 0;

        System.out.println("Welcome to the Number Guessing Game");

        while (PlayAgain) {
            int LowerBound = 1;
            int UpperBound = 100;
            int MaxAttempts = 5;
            int RandomNumber = random.nextInt(UpperBound - LowerBound + 1) + LowerBound;

            System.out.printf("Guess the number between %d and %d. You have %d attempts.%n", LowerBound, UpperBound, MaxAttempts);

            boolean GuessedCorrectly = false;
            for (int attempt = 1; attempt <= MaxAttempts; attempt++) {
                System.out.printf("Attempt %d/%d: Enter your guess: ", attempt, MaxAttempts);
                int UserGuess = sc.nextInt();

                if (UserGuess == RandomNumber) {
                    System.out.println("Congratulations.....! You guessed the number correctly.");
                    GuessedCorrectly = true;
                    TotalScore += (MaxAttempts - attempt + 1);
                    break;
                } else if (UserGuess > RandomNumber) {
                    System.out.println("Too high.....Try again.");
                } else {
                    System.out.println("Too low....Try again.");
                }
            }

            if (!GuessedCorrectly) {
                System.out.printf("You have used all attempts....The correct number was %d.%n", RandomNumber);
            }

            System.out.printf("Your total score: %d%n", TotalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = sc.next();
            PlayAgain = response.equalsIgnoreCase("yes");
        }

        System.out.printf("Game over..Your final score: %d%n", TotalScore);
        sc.close();
    }
}



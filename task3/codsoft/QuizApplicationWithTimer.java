package com.task3.codsoft;

import java.util.*;

public class QuizApplicationWithTimer {
    static class Question {
        String QuestionText;
        String[] Options;
        int CorrectOption;

        public Question(String questionText, String[] options, int correctOption) {
            this.QuestionText = questionText;
            this.Options = options;
            this.CorrectOption = correctOption;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> qs = createQuizQuestions();
        int score = 0;
        Map<Integer, Boolean> results = new LinkedHashMap<>();
        int timeLimit = 10; // Time limit per question in seconds

        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have "+ timeLimit + " seconds to answer each question. Good luck!\n");

        for (int i = 0; i < qs.size(); i++) {
            Question question = qs.get(i);
            System.out.println("Question "+ (i + 1) + ": " + question.QuestionText);

            for (int j = 0; j < question.Options.length; j++) {
                System.out.printf("%d. %s%n", j + 1, question.Options[j]);
            }

            System.out.printf("You have %d seconds to answer. Enter your choice (1-%d): ", timeLimit, question.Options.length);

            long startTime = System.currentTimeMillis();
            int userChoice = -1;

            while (true) {
                if (sc.hasNextInt()) {
                    userChoice = sc.nextInt() - 1;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                }
                if ((System.currentTimeMillis() - startTime) / 1000 > timeLimit) {
                    break;
                }
            }

            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) / 1000 > timeLimit) {
                System.out.println("\nTime's up! Moving to the next question.\n");
                results.put(i, false);
                continue;
            }

            if (userChoice == question.CorrectOption) {
                System.out.println("Correct!\n");
                score++;
                results.put(i, true);
            } else {
                System.out.println("Wrong! The correct answer was: " + question.Options[question.CorrectOption] + "\n");
                results.put(i, false);
            }
        }

        // Display final results
        System.out.println("Quiz Over!");
        System.out.println("Your final score: "+ score + "/" + qs.size());
        System.out.println("Results Summary:");
        for (int i = 0; i < qs.size(); i++) {
            Question question = qs.get(i);
            System.out.println("Question "+ (i + 1) + ": " + (results.get(i) ? "Correct" : "Incorrect"));
            if (!results.get(i)) {
                System.out.println("Correct Answer: "+ question.Options[question.CorrectOption]);
            }
        }

        sc.close();
    }

    private static List<Question> createQuizQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"},
                2
        ));
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
                1
        ));
        questions.add(new Question(
                "What is the largest mammal?",
                new String[]{"Elephant", "Blue Whale", "Giraffe", "Tiger"},
                1
        ));
        questions.add(new Question(
                "Who wrote 'Romeo and Juliet'?",
                new String[]{"Shakespeare", "Hemingway", "Tolkien", "Austen"},
                0
        ));
        return questions;
    }
}

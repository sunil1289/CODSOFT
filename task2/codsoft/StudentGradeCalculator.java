package com.task2.codsoft;

    import java.util.Scanner;

    public class StudentGradeCalculator {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to the Grade Calculator");


            System.out.print("Enter the number of subjects: ");
            int NumberOfSubjects = sc.nextInt();

            int[] marks = new int[NumberOfSubjects];
            int TotalMarks = 0;


            for (int i = 0; i < NumberOfSubjects; i++) {
                System.out.printf("Enter marks for subject %d (out of 100): ", i + 1);
                marks[i] = sc.nextInt();

                while (marks[i] < 0 || marks[i] > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    System.out.printf("Enter marks for subject %d (out of 100): ", i + 1);
                    marks[i] = sc.nextInt();
                }
                TotalMarks += marks[i];
            }

            // Calculating average percentage
            double AveragePercentage = (double) TotalMarks / NumberOfSubjects;


            char grade;
            if (AveragePercentage >= 90) {
                grade = 'A';
            } else if (AveragePercentage >= 75) {
                grade = 'B';
            } else if (AveragePercentage >= 50) {
                grade = 'C';
            } else if (AveragePercentage >= 35) {
                grade = 'D';
            } else {
                grade = 'F';
            }


            System.out.println("\n--- Results ---");
            System.out.println("Total Marks: " + TotalMarks);
            System.out.printf("Average Percentage: %.2f%%%n", AveragePercentage);
            System.out.println("Grade: " + grade);

            sc.close();
        }
    }



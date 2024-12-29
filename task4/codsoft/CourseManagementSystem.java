package com.task4.codsoft;

import java.util.*;

public class CourseManagementSystem {
    private static List<Course> Courses = new ArrayList<>();
    private static Map<String, Student> Students = new HashMap<>();

    public static void main(String[] args) {
        InitializeCourses();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Course Management System!");

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Display Courses");
            System.out.println("2. Register Student");
            System.out.println("3. Enroll in a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View Student Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    DisplayCourses();
                    break;
                case 2:
                    RegisterStudent(sc);
                    break;
                case 3:
                    EnrollInCourse(sc);
                    break;
                case 4:
                    DropCourse(sc);
                    break;
                case 5:
                    ViewStudentCourses(sc);
                    break;
                case 6:
                    System.out.println("Thank you for using the Course Management System.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void InitializeCourses() {
        Courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 50, "Mon 10:00 AM"));
        Courses.add(new Course("MA101", "Calculus I", "Introduction to differential calculus", 40, "Tue 11:00 AM"));
        Courses.add(new Course("PH101", "Physics I", "Basics of mechanics and motion", 30, "Wed 9:00 AM"));
    }

    private static void DisplayCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : Courses) {
            System.out.println(course);
        }
    }

    private static void RegisterStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();


        if(studentId.isEmpty()){
            System.out.println("Student ID cannot be empty");
            return;
        }
        if(name.isEmpty()){
            System.out.println("Student name cannot be empty");
            return;
        }

        if (Students.containsKey(studentId)) {
            System.out.println("Student already registered.");
        } else {
            Students.put(studentId, new Student(studentId, name));
            System.out.println("Student registered successfully.");
        }
    }

    private static void EnrollInCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = Students.get(studentId);

        if (student == null) {
            System.out.println("Student not found. Please register first.");
            return;
        }

        DisplayCourses();
        System.out.print("Enter Course Code to Enroll: ");
        String courseCode = scanner.nextLine();

        Course course = Courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println("Course not found.");
        } else if (student.registerCourse(course)) {
            System.out.println("Enrolled in course successfully.");
        } else {
            System.out.println("Enrollment failed.");
        }
    }

    private static void DropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = Students.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses to drop.");
            return;
        }

        System.out.println("Registered Courses ");
        for (Course course : registeredCourses) {
            System.out.println(course.getCode() + ": " + course.getTitle());
        }

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine();

        Course course = registeredCourses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println("You are not enrolled in this course.");
        } else if (student.DropCourse(course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Failed to drop the course.");
        }
    }

    private static void ViewStudentCourses(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = Students.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Course> RegisteredCourses = student.getRegisteredCourses();
        if (RegisteredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            System.out.println("\n--- Registered Courses ---");
            for (Course course : RegisteredCourses) {
                System.out.println(course);
            }
        }
    }
}

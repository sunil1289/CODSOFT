package com.task4.codsoft;

import java.util.ArrayList;
import java.util.List;

class Student {
    private String StudentId;
    private String Name;
    private List<Course> RegisteredCourses;

    public Student(String studentId, String name) {
        this.StudentId = studentId;
        this.Name = name;
        this.RegisteredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return StudentId;
    }

    public String getName() {
        return Name;
    }

    public List<Course> getRegisteredCourses() {
        return RegisteredCourses;
    }

    public boolean registerCourse(Course course) {
        if (!RegisteredCourses.contains(course) && course.enrollStudent()) {
            RegisteredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean DropCourse(Course course) {
        if (RegisteredCourses.contains(course)) {
            RegisteredCourses.remove(course);
            course.DropStudent();
            return true;
        }
        return false;
    }
}


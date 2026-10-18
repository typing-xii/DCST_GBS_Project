/*
 * Project: DCST-GBS Student Information System
 * Author: Vinay Sharma
 * Roll Number: 2659
 * Purpose: Stores a student's course and displays student record details.
 */
/** Represents a student and inherits shared identity fields from Person. */
public class Student extends Person {
    // Course is stored as text; the project has no separate course catalogue.
    private final String course;

    /** Initializes inherited identity data and validates the student's course. */
    public Student(String id, String name, String course) {
        super(id, name);
        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }
        this.course = course.trim();
    }

    /** Returns the student's course text. */
    public String getCourse() {
        return course;
    }

    /** Implements Person.display() with student-specific fields. */
    @Override
    public void display() {
        System.out.println("Student ID: " + getId()
                + " | Name: " + name
                + " | Course: " + course);
    }

    /** Returns a compact text representation of this student. */
    @Override
    public String toString() {
        return getId() + " - " + name + " - " + course;
    }
}

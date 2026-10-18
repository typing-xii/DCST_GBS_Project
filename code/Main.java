import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Project: DCST-GBS Student Information System
 * Author: Vinay Sharma
 * Roll Number: 2659
 * Purpose: Runs the terminal menu for entering, displaying, and summarizing records.
 */
/** Provides the command-line entry point and menu actions for the application. */
public class Main {
    // The current session's input reader and in-memory record collections.
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final ArrayList<Staff> staffMembers = new ArrayList<>();
    private static final ArrayList<Activity> activities = new ArrayList<>();

    /** Displays the menu, dispatches the selected action, and closes input on exit. */
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("===== DCST-GBS INFORMATION SYSTEM =====");
            System.out.println("1. Add student");
            System.out.println("2. Add staff");
            System.out.println("3. Add activity");
            System.out.println("4. Display all records");
            System.out.println("5. Summary report");
            System.out.println("6. Save summary to file");
            System.out.println("7. Demonstrate OOP concepts");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = input.nextLine().trim();

            // Invalid values raised by an action are reported without ending the menu.
            try {
                switch (choice) {
                    case "1" -> addStudent();
                    case "2" -> addStaff();
                    case "3" -> addActivity();
                    case "4" -> displayRecords();
                    case "5" -> showSummary();
                    case "6" -> saveSummary();
                    case "7" -> OOPDemo.run();
                    case "0" -> running = false;
                    default -> System.out.println("Please enter a number from the menu.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
            } finally {
                // The finally block runs after each menu action.
            }
        }

        input.close();
        System.out.println("Program closed.");
    }

    /** Prompts for a non-empty value and returns its trimmed text. */
    private static String ask(String message) {
        System.out.print(message);
        String value = input.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("This field cannot be empty.");
        }
        return value;
    }

    /** Reads student details and adds a Student object to the session list. */
    private static void addStudent() {
        String id = ask("Student ID: ");
        String name = ask("Student name: ");
        String course = ask("Course: ");
        students.add(new Student(id, name, course));
        System.out.println("Student added.");
    }

    /** Reads staff details, including a Department enum value, and stores the record. */
    private static void addStaff() {
        String id = ask("Staff ID: ");
        String name = ask("Staff name: ");
        String designation = ask("Designation: ");
        Department department = Department.valueOf(
                ask("Department (DCST or GBS): ").toUpperCase());
        staffMembers.add(new Staff(id, name, designation, department));
        System.out.println("Staff added.");
    }

    /** Reads activity details and stores a record for the chosen department and year. */
    private static void addActivity() {
        String name = ask("Activity name (e.g. InfoFest or ISA): ");
        String type = ask("Type (Academic or Non-academic): ");
        Department department = Department.valueOf(
                ask("Department (DCST or GBS): ").toUpperCase());
        String year = ask("Academic year (e.g. 2025-26): ");
        activities.add(new Activity(name, type, department, year));
        System.out.println("Activity added.");
    }

    /** Displays all records currently held in the three in-memory lists. */
    private static void displayRecords() {
        System.out.println("----- STUDENTS -----");
        if (students.isEmpty()) System.out.println("No students added.");
        for (Student student : students) student.display();

        System.out.println("----- STAFF -----");
        if (staffMembers.isEmpty()) System.out.println("No staff added.");
        for (Staff staff : staffMembers) staff.display();

        System.out.println("----- ACTIVITIES -----");
        if (activities.isEmpty()) System.out.println("No activities added.");
        for (Activity activity : activities) System.out.println(activity);
    }

    /** Prints the record totals and the shared Person object count. */
    private static void showSummary() {
        System.out.println("----- SUMMARY REPORT -----");
        System.out.println("Students: " + students.size());
        System.out.println("Staff: " + staffMembers.size());
        System.out.println("Activities: " + activities.size());
        System.out.println("Total Person objects created: " + Person.getCount());
    }

    /** Writes a summary (not the full record lists) to report.txt. */
    private static void saveSummary() {
        String report = "DCST-GBS SUMMARY REPORT\\n"
                + "Students: " + students.size() + "\\n"
                + "Staff: " + staffMembers.size() + "\\n"
                + "Activities: " + activities.size() + "\\n";
        try {
            Files.writeString(Path.of("report.txt"), report);
            System.out.println("Summary saved in report.txt.");
        } catch (IOException e) {
            System.out.println("Could not save file: " + e.getMessage());
        } finally {
            System.out.println("File operation finished.");
        }
    }
}

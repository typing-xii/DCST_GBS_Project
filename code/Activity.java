/*
 * Project: DCST-GBS Student Information System
 * Author: Vinay Sharma
 * Roll Number: 2659
 * Purpose: Stores the name, category, department, and academic year of an activity.
 */
/** Represents one academic or non-academic activity record. */
public class Activity {
    private final String name;
    private final String type;
    private final Department department;
    private final String academicYear;

    /** Validates the activity name and initializes all activity attributes. */
    public Activity(String name, String type, Department department, String academicYear) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Activity name cannot be empty.");
        }
        this.name = name.trim();
        this.type = (type == null || type.isBlank()) ? "Other" : type.trim();
        this.department = department == null ? Department.DCST : department;
        this.academicYear = (academicYear == null || academicYear.isBlank())
                ? "Not specified" : academicYear.trim();
    }

    /** Returns the year associated with this activity. */
    public String getAcademicYear() {
        return academicYear;
    }

    /** Formats the activity fields for terminal display. */
    @Override
    public String toString() {
        return name + " | " + type + " | " + department + " | " + academicYear;
    }
}

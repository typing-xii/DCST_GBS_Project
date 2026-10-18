/*
 * Project: DCST-GBS Student Information System
 * Author: Vinay Sharma
 * Roll Number: 2659
 * Purpose: Stores staff details and displays a staff record.
 */
/** Represents a staff member as a specialized Person. */
public class Staff extends Person {
    private final String designation;
    private final Department department;

    /** Initializes the common person data and validates staff-specific fields. */
    public Staff(String id, String name, String designation, Department department) {
        super(id, name);
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation cannot be empty.");
        }
        if (department == null) {
            throw new IllegalArgumentException("Department must be selected.");
        }
        this.designation = designation.trim();
        this.department = department;
    }

    /** Implements Person.display() with staff-specific fields. */
    @Override
    public void display() {
        System.out.println("Staff ID: " + getId()
                + " | Name: " + name
                + " | Designation: " + designation
                + " | Department: " + department);
    }

    /** Returns a compact text representation of this staff member. */
    @Override
    public String toString() {
        return getId() + " - " + name + " - " + designation + " - " + department;
    }
}

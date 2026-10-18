/*
 * Project: DCST-GBS Student Information System
 * Author: Vinay Sharma
 * Roll Number: 2659
 * Purpose: Defines common identity data and behavior for students and staff.
 */
/** Abstract parent type shared by Student and Staff. */
public abstract class Person {
    // Private identity is exposed to other classes through getId().
    private final String id;
    // Subclasses can use the name directly; external classes use getName().
    protected String name;
    // One class-level counter shared by every Person subclass instance.
    private static int count = 0;

    /** Validates and stores the common identity fields and counts this object. */
    public Person(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.id = id.trim();
        this.name = name.trim();
        count++;
    }

    /** Returns the private ID without exposing the field itself. */
    public String getId() {
        return id;
    }

    /** Returns the person's name. */
    public String getName() {
        return name;
    }

    /** Returns the total number of Person objects constructed so far. */
    public static int getCount() {
        return count;
    }

    /** Requires each concrete subclass to display its own record details. */
    public abstract void display();
}

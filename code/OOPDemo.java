import java.lang.reflect.Field;
import java.util.*;

/*
 * Project: DCST-GBS Student Information System
 * Author: Vinay Sharma
 * Roll Number: 2659
 * Purpose: Runs examples of OOP features already present in this project.
 */
/** Contains small runnable demonstrations used by the menu's OOP option. */
public class OOPDemo {

    // Multiple interface implementation: one class can implement both contracts.
    interface Printable {
        void print();
    }

    interface Checkable {
        void check();
    }

    /** Implements two interfaces; Java classes can extend only one class. */
    static class Example implements Printable, Checkable {
        /** Implements the Printable operation. */
        public void print() {
            System.out.println("Printing a report.");
        }

        /** Implements the Checkable operation. */
        public void check() {
            System.out.println("Checking a report.");
        }
    }

    /** Supplies the Reportable interface implementation used by printReport. */
    static class SimpleReport implements Reportable {
        public String createReport() {
            return "DCST-GBS demonstration report";
        }
    }

    /** Holds a mutable list to demonstrate shared and independent list copies. */
    static class ActivityLog {
        ArrayList<String> items = new ArrayList<>();

        /** Shares the same list object, so mutations are visible through both logs. */
        ActivityLog shallowCopy() {
            ActivityLog copy = new ActivityLog();
            copy.items = items;
            return copy;
        }

        /** Makes a distinct list containing the current string entries. */
        ActivityLog deepCopy() {
            ActivityLog copy = new ActivityLog();
            copy.items = new ArrayList<>(items);
            return copy;
        }
    }

    // Same method name with different parameter types demonstrates overloading.
    static void showMessage(String message) {
        System.out.println(message);
    }

    static void showMessage(int number) {
        System.out.println("Number of records: " + number);
    }

    // Accepting the interface type allows any Reportable implementation to be passed.
    static void printReport(Reportable report) {
        System.out.println(report.createReport());
    }

    /** Runs the console examples selected from Main's menu. */
    public static void run() {
        System.out.println("\\n--- OOP CONCEPT DEMONSTRATIONS ---");

        // 1. Constructor initialization and two references to the same Student object.
        Student first = new Student("D1", "Sample Student", "BCA");
        Student second = first;
        second.name = "Name Changed Through Shared Reference";
        System.out.println("1. Shared reference: " + first.getName());

        // 2. Reads a class-level counter and uses a fixed Department enum value.
        System.out.println("2. Person objects created: " + Person.getCount());
        System.out.println("   Department enum: " + Department.DCST);

        // 3. Multiple interfaces
        Example example = new Example();
        System.out.print("3. ");
        example.print();
        example.check();
        System.out.println("   Java classes can implement multiple interfaces, "
                + "but cannot extend two classes.");

        // 4. Access modifiers: private ID through public getter
        System.out.println("4. Private ID accessed through getter: " + first.getId());

        // 5. Person is abstract; this parent-type reference dispatches to Student.display().
        Person person = first;
        System.out.print("5. Abstract Person reference: ");
        person.display();

        // 6. Interface as a type
        System.out.print("6. Interface message passing: ");
        printReport(new SimpleReport());

        // 7. Overloading
        System.out.print("7. ");
        showMessage("This is the text version of an overloaded method.");
        showMessage(2);

        // 8. Generic collections
        List<String> list = new ArrayList<>();
        list.add("DCST");
        Queue<String> queue = new LinkedList<>();
        queue.add("GBS");
        Set<String> set = new HashSet<>(list);
        set.add("GBS");
        Map<String, Integer> map = new HashMap<>();
        map.put("Students", 1);
        map.put("Staff", 1);
        System.out.println("8. List: " + list);
        System.out.println("   Queue: " + queue);
        System.out.println("   Set: " + set);
        System.out.println("   Map: " + map);

        // 9. This is a built-in exception used to demonstrate try/catch/finally,
        // not a separately declared custom exception class.
        try {
            throw new IllegalArgumentException("Example custom validation message.");
        } catch (IllegalArgumentException e) {
            System.out.println("9. Caught exception: " + e.getMessage());
        } finally {
            System.out.println("   Finally block executed.");
        }

        // 10. Reflection inspects the runtime class and declared Student fields.
        System.out.println("10. Runtime class: " + first.getClass().getName());
        for (Field field : Student.class.getDeclaredFields()) {
            System.out.println("    Student field: " + field.getName());
        }

        // These manual copy methods illustrate shallow/deep copying; Object.clone()
        // and the Cloneable interface are not used in this project.
        ActivityLog original = new ActivityLog();
        original.items.add("ISA");
        ActivityLog shallow = original.shallowCopy();
        ActivityLog deep = original.deepCopy();
        shallow.items.add("SEA");
        System.out.println("    Original after shallow copy: " + original.items);
        System.out.println("    Independent deep copy: " + deep.items);
        System.out.println("---------------------------------\\n");
    }
}

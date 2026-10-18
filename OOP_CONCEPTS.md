# OOP Concepts in the DCST–GBS Student Information System

This document describes what the current Java source demonstrates. Where the assignment requirement is not present, that is stated explicitly rather than added to the program.

All Java file references below refer to files in the project's `code/` folder.

## 1. Object referencing and constructors

**Meaning:** A constructor initializes an object when it is created. Two reference variables can refer to the same object; changing that object through either reference is visible through the other reference.

**In this project:** Constructors initialize attributes in `Person(String id, String name)`, `Student(String id, String name, String course)`, `Staff(String id, String name, String designation, Department department)`, and `Activity(String name, String type, Department department, String academicYear)`. The constructors validate required values and trim stored text. In `OOPDemo.run`, `Student second = first` makes both variables refer to one student; assigning `second.name` changes the same object, and `first.getName()` displays the changed name.

## 2. Static attributes, methods, and enums

**Meaning:** A static field or method belongs to the class rather than to one particular object. An enum defines a fixed set of named values.

**In this project:** `Person.count` is a private static counter incremented by the `Person` constructor. `Person.getCount()` returns it, and `Main.showSummary()` and `OOPDemo.run()` display it. `Department.java` defines the `DCST` and `GBS` enum values, used by `Main.addStaff`, `Main.addActivity`, `Staff`, and `Activity`.

Static object counters, a static count-access method, and an enum are demonstrated. There are no separate static counting fields/methods for students, staff, or activities.

## 3. Multiple inheritance

**Meaning:** Java classes can extend only one class, but a class can implement more than one interface. This lets a class provide behavior required by several interfaces without inheriting implementation from multiple classes.

**In this project:** `OOPDemo.Example` implements both `OOPDemo.Printable` and `OOPDemo.Checkable`, providing `print()` and `check()`. `Student` and `Staff` each extend the single class `Person`. Java class multiple inheritance (extending multiple classes) is not supported and is not implemented here. Multiple interface implementation is demonstrated; there is no example of one interface extending multiple interfaces.

## 4. Access modifiers

**Meaning:** `public` members are accessible wherever their containing type is accessible; `private` members are restricted to their declaring class; `protected` members are accessible within the declaring package and in subclasses, subject to Java's protected-access rules.

**In this project:** `Person.id` is private and is read through the public `getId()` method, including in `OOPDemo.run()`. `Person.name` is protected and is used by the `Student` and `Staff` subclasses. Constructors and methods such as `Person.getName()`, `Person.getCount()`, and `Student.display()` are public. Private helper methods and fields in `Main` show class-internal access. The source demonstrates these modifiers, though it does not include an explicit cross-package access test.

## 5. Abstract classes and interfaces

**Meaning:** An abstract class can hold shared state and behavior while requiring subclasses to implement abstract methods. An interface specifies methods a class agrees to provide.

**In this project:** `Person` is abstract and declares `display()` as abstract. `Student.display()` and `Staff.display()` implement it differently. `Reportable` declares `createReport()`, implemented by `OOPDemo.SimpleReport`; `OOPDemo.Printable` and `OOPDemo.Checkable` are additional nested interfaces implemented by `OOPDemo.Example`.

## 6. Class as a type and message passing

**Meaning:** An interface type can be used as a parameter type. Passing an implementing object to a function and invoking the interface method lets that function work with different implementations through a common contract.

**In this project:** `OOPDemo.printReport(Reportable report)` accepts the interface type and calls `report.createReport()`. `OOPDemo.run()` passes `new SimpleReport()` to it. This is a concrete example of passing a message (a method call) through an interface-typed parameter.

## 7. Polymorphism

**Meaning:** Overriding lets a subclass supply its own implementation of an inherited method. Overloading provides multiple methods with the same name but different parameter lists.

**In this project:** `Student.display()` and `Staff.display()` override `Person.display()`. In `OOPDemo.run()`, a `Person person = first` reference invokes `person.display()` and uses the `Student` implementation at runtime. `OOPDemo.showMessage(String)` and `OOPDemo.showMessage(int)` demonstrate overloading. `Student` and `Staff` also override `toString()`.

## 8. Generics and the Collection Framework

**Meaning:** Generics provide compile-time type information for reusable types and methods. Java's collection interfaces include `List`, `Queue`, `Set`, and `Map`.

**In this project:** `Main` uses `ArrayList<Student>`, `ArrayList<Staff>`, and `ArrayList<Activity>`. `OOPDemo.run()` declares `List<String>`, `Queue<String>`, `Set<String>`, and `Map<String, Integer>` values, backed by `ArrayList`, `LinkedList`, `HashSet`, and `HashMap`. `OOPDemo.ActivityLog` stores an `ArrayList<String>`.

Generic collection types are used. A user-defined generic class or generic method (for example, one declaring its own type parameter such as `<T>`) is **Not implemented in the current code**.

## 9. Exception handling

**Meaning:** `try` encloses code that may fail, `catch` handles a matching exception, and `finally` runs after the try/catch flow. Code can throw an exception to report invalid input or another failure.

**In this project:** `Main.main()` catches `IllegalArgumentException` from menu actions and has a `finally` block. `Main.saveSummary()` catches `IOException` and also uses `finally`. `Person`, `Student`, `Staff`, and `Activity` throw `IllegalArgumentException` when required data is invalid. `OOPDemo.run()` explicitly throws and catches `IllegalArgumentException` to demonstrate the flow.

Runtime exception handling and built-in exceptions are demonstrated. A separately declared custom exception class is **Not implemented in the current code**.

## 10. Reflection, persistence, I/O, and cloning

**Meaning:** Reflection inspects types and members at runtime. Persistence stores information beyond the lifetime of the current process. Console and file I/O exchange information with a user or a file. A shallow copy shares referenced objects; a deep copy creates independent copies of nested mutable data.

**In this project:**

- **Reflection/RTTI:** `OOPDemo.run()` calls `first.getClass().getName()` for runtime type information and uses `Student.class.getDeclaredFields()` to inspect field names.
- **Persistence:** `Main.saveSummary()` uses `Files.writeString(Path.of("report.txt"), report)` to write a summary file. The record collections themselves remain in memory and are not saved or loaded.
- **Console and file I/O:** `Main` uses `Scanner` and `System.out`/`System.in` for the terminal menu and prompts. `Main.saveSummary()` writes a file and reports `IOException`. Reading records from a file is **Not implemented in the current code**.
- **Copying:** `OOPDemo.ActivityLog.shallowCopy()` shares the original `items` list, while `deepCopy()` creates a new list containing the current strings. `OOPDemo.run()` demonstrates the difference by changing the shallow copy's list.

The copy methods illustrate shallow and deep copying, but implementation using Java's `Cloneable` and `Object.clone()` is **Not implemented in the current code**. Full record persistence (saving and restoring students, staff, and activities) is also **Not implemented in the current code**.

## Requirement-to-file mapping

| Assignment requirement | Existing file(s) | Current implementation / status |
|---|---|---|
| 1. Shared object references and constructors | `OOPDemo.java`, `Person.java`, `Student.java`, `Staff.java`, `Activity.java` | `OOPDemo.run()` shows two references to one `Student`; the constructors initialize and validate fields. |
| 2. Static attributes/methods and enums | `Person.java`, `Department.java`, `Main.java`, `OOPDemo.java` | `Person.count` and `Person.getCount()` count created people; `Department` defines `DCST` and `GBS`. |
| 3. Multiple inheritance | `OOPDemo.java`, `Student.java`, `Staff.java`, `Person.java` | `OOPDemo.Example` implements two interfaces; students and staff each extend one class. Extending multiple classes is unsupported in Java and not implemented. Multiple-interface extension is not implemented. |
| 4. `public`, `private`, and `protected` access | `Person.java`, `Student.java`, `Staff.java`, `Main.java` | Demonstrated by private ID, protected name, public getters/methods, and private members. No cross-package access example. |
| 5. Abstract classes and interfaces | `Person.java`, `Student.java`, `Staff.java`, `Reportable.java`, `OOPDemo.java` | Abstract `Person.display()` is implemented differently by subclasses; report and nested interfaces are implemented by demo classes. |
| 6. Interface-typed parameter and message passing | `Reportable.java`, `OOPDemo.java` | `printReport(Reportable report)` calls `createReport()` on a passed `SimpleReport`. |
| 7. Overriding and overloading | `Person.java`, `Student.java`, `Staff.java`, `OOPDemo.java` | Subclasses override `display()`; `showMessage` is overloaded for `String` and `int`. |
| 8. Generics and `List`, `Queue`, `Set`, `Map` | `Main.java`, `OOPDemo.java` | Generic collection types and all four collection interfaces are used. User-defined generic class/method: **Not implemented in the current code**. |
| 9. `try`, `catch`, `finally`, throwing, custom exceptions | `Main.java`, `Person.java`, `Student.java`, `Staff.java`, `Activity.java`, `OOPDemo.java` | Built-in `IllegalArgumentException` is thrown/caught; `IOException` is handled; try/catch/finally are present. Separate custom exception class: **Not implemented in the current code**. |
| 10. Reflection, persistence, I/O, cloning | `OOPDemo.java`, `Main.java` | Reflection, console I/O, summary-file output, and manual shallow/deep copying are demonstrated. Full data persistence and `Cloneable`/`Object.clone()` are **Not implemented in the current code**. |

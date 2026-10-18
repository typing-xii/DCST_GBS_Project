# DCST–GBS Student Information System

## Project information

| Item | Details |
|---|---|
| Project name | DCST–GBS Student Information System |
| Author | Vinay Sharma |
| Roll number | 2659 |
| Institution | Goa University |
| Programming language | Java |
| Application type | CMD/terminal-based application |
| Purpose | Managing and reporting information about DCST and GBS students, staff, academic activities, non-academic activities, programmes, courses, and subjects for a three-year NIRF reporting period. |

## Folder structure

```text
DCSTGBSProject/
├── code/                 Java source files
├── classes/              Compiled .class files
├── README.md
└── OOP_CONCEPTS.md
```

All eight existing Java source files are in `code/`; compiled output is kept separately in `classes/`. Source comments identify the project, author, roll number, file purpose, key operations, and relevant OOP examples.

The current program provides a small command-line record manager for students, staff, and activities. Its menu can display records and counts, save a summary to a text file, and run demonstrations of OOP concepts. The source currently models a student's course as text and activities as named records; it does not contain a separate catalogue of programmes, courses, or subjects or enforce a three-year reporting period.

## Java files

### `code/Main.java`

Defines the `Main` class and the command-line entry point, `main(String[] args)`. It owns a `Scanner` and in-memory `ArrayList` collections for `Student`, `Staff`, and `Activity` records. The private methods `ask`, `addStudent`, `addStaff`, and `addActivity` read and validate menu input and create records. `displayRecords` prints the records, `showSummary` prints record totals and the `Person` object count, and `saveSummary` writes the summary to `report.txt`. The `main` method shows the menu, dispatches choices, handles `IllegalArgumentException`, and closes the input scanner on exit. It connects the menu to `Student`, `Staff`, `Activity`, `Department`, `Person`, and `OOPDemo`.

### `code/Person.java`

Defines the abstract base class `Person`, shared by students and staff. Its constructor validates and stores an ID and name, then increments the static object counter. The `getId`, `getName`, and static `getCount` methods expose those values, while the abstract `display` method requires subclasses to provide their own display behavior. The `id` is private, `name` is protected, and the counter is private and static. `Student` and `Staff` extend this class.

### `code/Student.java`

Defines `Student`, a `Person` subclass. Its constructor calls `super` to initialize the inherited ID and name, validates and stores a course, and `getCourse` returns that course. It overrides `display` to print student details and `toString` to provide a text representation. The class is created and displayed by `Main`, and is also used in `OOPDemo` to demonstrate shared references, inheritance, and reflection.

### `code/Staff.java`

Defines `Staff`, a `Person` subclass. Its constructor initializes inherited person details and validates and stores a designation and `Department`. It overrides `display` and `toString` to represent staff details. `Main` creates and displays staff records; the department value is from `Department.java`.

### `code/Activity.java`

Defines an `Activity` record with private final fields for name, type, department, and academic year. Its constructor validates the name and supplies defaults for blank or null optional values. `getAcademicYear` returns the year, and `toString` formats all four activity values. `Main` creates and lists activities. The activity's department uses the `Department` enum.

### `code/Department.java`

Defines the `Department` enum with the values `DCST` and `GBS`. `Main` reads these values for staff and activity records, while `Staff` and `Activity` store them as typed department fields. `OOPDemo.run` also prints `Department.DCST`.

### `code/Reportable.java`

Defines the `Reportable` interface with the `createReport()` method, which returns a `String`. `OOPDemo.SimpleReport` implements the interface, and `OOPDemo.printReport` accepts a `Reportable` value and calls that method. This is a small example of programming to an interface; the main student/staff/activity records do not implement this interface.

### `code/OOPDemo.java`

Defines `OOPDemo`, whose public static `run` method displays examples of OOP concepts when selected from the `Main` menu. Its nested `Printable` and `Checkable` interfaces and `Example` class demonstrate implementing multiple interfaces; `SimpleReport` implements `Reportable`. `ActivityLog` has `shallowCopy` and `deepCopy` methods for showing shared versus independent lists. The overloaded `showMessage` methods demonstrate method overloading, while `printReport(Reportable report)` demonstrates interface-typed message passing. `run` also demonstrates shared object references, the `Person` counter, the department enum, a `Person` reference to a `Student`, generic collection interfaces, exception handling, and reflection. The demonstrations use `Student`, `Person`, `Department`, and `Reportable`.

## Existing application features

- Add students with an ID, name, and course.
- Add staff with an ID, name, designation, and department (`DCST` or `GBS`).
- Add activities with a name, type, department, and academic year.
- Display all records held during the current run.
- Display counts for students, staff, activities, and created `Person` objects.
- Save a summary to `report.txt` in the program's current working directory.
- Run the OOP examples through menu option 7.

Records are held in memory while the program runs. The save option writes a summary only; the program does not save and reload all records. Student courses are stored as strings, and activities are represented by a general `Activity` class. No separate programme or subject records are defined.

## Case study

The assignment describes the system as supporting three-year reporting for DCST and GBS. The existing code has a department enum, an academic-year field on activities, and a summary of the current in-memory records, but it does not enforce a three-year period or implement a dedicated NIRF report.

The assignment's example categories include academic activities—ISA, Midpoint, SEA, study tours, placements, internships, workshops, seminars, guest lectures, industrial visits, and project presentations—and non-academic activities—InfoFest, Plateaunia, Play-on, cultural events, sports, student clubs, social outreach, and volunteering. In the current program, these can be entered as activity names and a free-text type; the source does not enumerate or confirm that any listed activity is officially conducted.

The student class has a course field represented by a string. The source does not define separate programme, course-catalogue, or subject classes or establish which programmes, courses, or subjects are offered.

## Compile and run

Use a JDK that supports the language features used in the source (JDK 14 or later). Open Command Prompt in the project root, then compile the Java source files into the `classes` folder:

```cmd
javac -d classes code\*.java
```

Start the menu with:

```cmd
java -cp classes Main
```

Choose a menu number and follow the prompts to add or display records, show the summary, save its summary text, or run the OOP demonstrations. Option `0` exits. Option `6` saves the summary to `report.txt` relative to the working directory from which the program was started.

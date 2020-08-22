package task;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A Task with no defined datetime
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean completed, String description) {
        super(completed, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCSV() {
        return "T," + super.toCSV();
    }

    public static Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct task from csv
        return new ToDo(
                Boolean.parseBoolean(scanner.next()),
                scanner.next()
        );
    }
}

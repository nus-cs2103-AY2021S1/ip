package duke.task;

import java.util.Scanner;

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
    public String toCsv() {
        return TaskEnum.TODO + "," + super.toCsv();
    }

    public static Task fromCsv(String csv) {
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct duke.task from csv
        return new ToDo(
                Boolean.parseBoolean(scanner.next()),
                scanner.next()
        );
    }
}

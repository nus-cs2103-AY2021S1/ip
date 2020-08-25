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

    /**
     * Get the csv representation of this task
     * @return A csv String representative of this task
     */
    @Override
    public String toCsv() {
        return TaskEnum.TODO + "," + super.toCsv();
    }

    /**
     * Initialize a task_todo instance from it's csv representation
     * @param csv A task_todo in csv format
     * @return The task_todo represented by the csv
     * @throws Exception If csv cannot be parsed into a task_todo object
     */
    public static Task fromCsv(String csv) throws Exception {
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

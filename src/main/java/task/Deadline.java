package task;

import misc.DukeDateTime;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

/**
 * A Task with a DukeDateTime indicating it's deadline
 */
public class Deadline extends Task {

    private final DukeDateTime deadline;

    public Deadline(String description, DukeDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean completed, String description, DukeDateTime deadline) {
        super(completed, description);
        this.deadline = deadline;
    }
    
    public LocalDateTime getDeadline() {
        return this.deadline.get();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.pretty() + ")";
    }

    @Override
    public String toCSV() {
        return "D," + super.toCSV() + "," + deadline;
    }

    // Warning: does not check for corrupt entry
    public static Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct task from csv
        return new Deadline(
                Boolean.parseBoolean(scanner.next()),
                scanner.next(),
                DukeDateTime.generate(scanner.next())
        );
    }
}

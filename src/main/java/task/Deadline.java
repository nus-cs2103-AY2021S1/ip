package task;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

/**
 * A Deadline is a Task with a deadline
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

    @Override
    public Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        Pattern pattern = Pattern.compile("([^,]+?),");
        return new Deadline(
                scanner.nextBoolean(),
                scanner.next(pattern),
                DukeDateTime.generate(scanner.next(pattern))
        );
    }
}

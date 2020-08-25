package duke.task;

import duke.DukeDateTime;

import java.util.Objects;
import java.util.Scanner;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Deadline)) return false;
        if (!super.equals(obj)) return false;
        Deadline deadline1 = (Deadline) obj;
        return getDeadline().equals(deadline1.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDeadline());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.pretty() + ")";
    }

    @Override
    public String toCsv() {
        return TaskEnum.DEADLINE + "," + super.toCsv() + "," + deadline;
    }

    // Warning: does not check for corrupt entry
    public static Task fromCsv(String csv) {
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct duke.task from csv
        return new Deadline(
                Boolean.parseBoolean(scanner.next()),
                scanner.next(),
                new DukeDateTime(scanner.next())
        );
    }
}

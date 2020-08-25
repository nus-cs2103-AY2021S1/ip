package duke.task;

import duke.DukeDateTime;

import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * A Task with a deadline
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

    /**
     * Get the csv representation of this task
     * @return A csv String representative of this task
     */
    @Override
    public String toCsv() {
        return TaskEnum.DEADLINE + "," + super.toCsv() + "," + deadline;
    }

    /**
     * Initialize a deadline instance from it's csv representation
     * @param csv A deadline in csv format
     * @return The deadline represented by the csv
     * @throws Exception If csv cannot be parsed into a deadline object
     */
    public static Task fromCsv(String csv) throws Exception {
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct task from csv
        return new Deadline(
                Boolean.parseBoolean(scanner.next()),
                scanner.next(),
                new DukeDateTime(scanner.next())
        );
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
}

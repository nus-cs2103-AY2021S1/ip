package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Command for add deadline tasks
 */
public class Deadline extends Task {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate deadline;

    /**
     * Deadline constructor
     *
     * @param description Description string without date
     * @param deadline Deadline LocalDate object
     * @param isDone Completion status of deadline
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description
                + " (by: " + this.deadline.format(formatter) + ")";
    }
}

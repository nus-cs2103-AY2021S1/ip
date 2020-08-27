package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class
 */
public class Deadline extends Task {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate deadline;

    /**
     * Deadline constructor
     *
     * @param description Description of Deadline task without dates
     * @param deadline Deadline LocalDate object
     * @param isDone Completion status of Deadline task
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns descriptions of a Deadline task with icons.
     *
     * @return descriptions of a Deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + this.deadline.format(formatter) + ")";
    }
}
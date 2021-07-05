package luke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task and its deadline for the user.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline object that indicates the task and its deadline.
     *
     * @param description details about the task
     * @param by deadline tha the task should be finished by
     */
    public Deadline(String description, LocalDate by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    public String getBy() {
        return stringifyBy();
    }

    private String stringifyBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH));
    }

    @Override
    public String toDataString() {
        return String.format("D|%s|%s", super.toDataString(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}

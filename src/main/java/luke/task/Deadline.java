package luke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    public String stringifyBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toDataString() {
        return String.format("D|%s|%s", super.toDataString(), this.getBy());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + stringifyBy() + ")";
    }
}

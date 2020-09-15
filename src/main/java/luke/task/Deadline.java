package luke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task and its deadline for the user.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline object that indicates the task and its deadline.
     *
     * @param description details about the task
     * @param by deadline tha the task should be finished by
     */
    public Deadline(String description, LocalDateTime by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    public String getBy() {
        return stringifyBy();
    }

    public String stringifyBy() {
        return by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
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

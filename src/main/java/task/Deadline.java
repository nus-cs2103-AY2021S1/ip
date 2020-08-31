package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.Deadline by its taskName, deadline and whether or not it has been completed.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String taskName, boolean isCompleted, LocalDateTime date) {
        super(taskName, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a")) + ")";
    }
}

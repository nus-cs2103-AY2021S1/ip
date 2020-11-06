package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a date and time attached to it.
 */

public abstract class TimedTask extends Task {
    private LocalDateTime datetime;

    /**
     * Creates a new TimedTask object.
     * @param name Name of the TimedTask.
     * @param isDone Whether the task is done or not.
     * @param datetime The date and time of occurrence the task.
     */
    public TimedTask(String name, boolean isDone, LocalDateTime datetime) {
        super(name, isDone);
        this.datetime = datetime;
    }
    public LocalDateTime getDateTime() {
        return datetime;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: "
                + getDateTime().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
    @Override
    public String writeString() {
        return super.writeString() + " # "
                + getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}

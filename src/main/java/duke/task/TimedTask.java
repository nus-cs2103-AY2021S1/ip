package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that that has a LocalDateTime attribute. A
 * <code>TimedTask</code> object extends a Task object.
 */
public abstract class TimedTask extends Task {

    /** Indicates when the Task will happen or is happening at */
    protected LocalDateTime dateTime;

    /**
     * Constructs a <code>TimedTask</code> object with a description and LocalDateTime.
     * This TimedTask is marked as undone.
     *
     * @param description A string representing the Task description.
     * @param dateTime A string representing the Task date/time.
     */
    public TimedTask(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Constructs a <code>TimedTask</code> object with a description,
     * a boolean to indicate if the Task is done and a LocalDateTime.
     *
     * @param description A string representing the Task description.
     * @param isDone Indicates if the Deadline is done.
     * @param dateTime A string representing the Task date/time.
     */
    public TimedTask(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns the LocalDate of the Task.
     *
     * @return A LocalDate object.
     */
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    /**
     * Returns the LocalTime of the Task.
     *a
     * @return A LocalTime object.
     */
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    /**
     * Returns the LocalDateTime of the Task.
     *
     * @return A LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}

package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specified date and time.
 */
public abstract class TimedTask extends Task {

    protected LocalDateTime dateTime;

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     * @param type        The type of this task.
     * @param dateTime    A string representing the task date/time.
     */
    public TimedTask(String description, TaskType type, String dateTime) {
        super(description, type);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     * @param type        The type of this task.
     * @param dateTime    A string representing the task date/time.
     */
    public TimedTask(String description, boolean isDone, TaskType type, String dateTime) {
        super(description, isDone, type);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String getTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String printTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
    }

    @Override
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    @Override
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

}

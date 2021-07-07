package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a {@code Task} which needs to be done before a specific date/time.
 */

public class Deadline extends Task implements TimeBased {
    private final DukeDateTime dateTime;

    /**
     * Constructor to create a new {@code Deadline} object which is initially uncompleted.
     *
     * @param content the content of what the {@code Deadline} task is about.
     * @param dateTime  a {@code DukeDateTime} object which represents the date and time of the {@code Deadline}.
     */
    public Deadline(String content, DukeDateTime dateTime) {
        super(content);
        this.dateTime = dateTime;
    }

    /**
     * Returns the due datetime of the {@code Deadline} object.
     *
     * @return the due datetime of the {@code Deadline} object.
     */
    @Override
    public LocalDateTime getDateTime() {
        LocalDateTime taskDateTime = this.dateTime.getDateTime();
        return taskDateTime;
    }

    /**
     * Returns the due date of the {@code Deadline} object.
     *
     * @return the due date of the {@code Deadline} object.
     */
    @Override
    public LocalDate getDate() {
        LocalDateTime taskLocalDateTime = this.getDateTime();
        LocalDate taskDate = taskLocalDateTime.toLocalDate();
        return taskDate;
    }

    /**
     * Returns the string representation of the {@code Deadline} object which has been formatted to the correct
     * format as the file in which it will be saved into.
     *
     * @return a string representation of the {@code Deadline} object which has been formatted accordingly to the
     * file specification which the task will be saved into.
     */
    @Override
    public String toDataFileFormat() {
        return String.format("D | %d | %s | %s | %s", isDone ? 1 : 0, tagName, content, dateTime);
    }

    /**
     * Returns a string representation of this {@code Deadline} object.
     *
     * @return a string representation of the {@code Deadline} task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

}

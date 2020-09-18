package meimei.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task to be completed by a given deadline. Inherits from Task.
 *
 * Adapted from <a href="https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html">
 *     this page</a> .
 */
public class Deadline extends Task {
    /** Represents the date and time at which to complete the task by */
    protected LocalDateTime dateTime;

    /**
     * Public constructor.
     *
     * @param taskName Name of the task as given by user.
     * @param dateTime LocalDateTime object representing the deadline of the task.
     */
    public Deadline(String taskName, LocalDateTime dateTime)  {
        super(taskName);
        this.dateTime = dateTime;
    }

    /**
     * Returns the date and time at which to complete the task by.
     *
     * @return LocalDateTime object representing the deadline of the task.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " +
            this.dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
    }
}
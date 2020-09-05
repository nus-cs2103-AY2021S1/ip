package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent task with specific date.
 */
public abstract class DateTask extends Task {
    protected LocalDateTime date;

    /**
     * Constructs a new DateTask with specified task name and date.
     * @param taskName the task's name
     * @param date the task's date
     */
    protected DateTask(String taskName, LocalDateTime date) {
        super(taskName);
        assert date != null : "Date can't be null!";
        this.date = date;
    }

    /**
     * Gets the task's date.
     * @return The task's date
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Gets the string representation of the date in the format of
     * yyyy-MM-dd HHmm e.g. 2020-01-01 1400 represents Jan 01 2019 2 pm.
     * @return A string representation of the date
     */
    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Sets the task's date to the specified date.
     * @param date the new task's date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

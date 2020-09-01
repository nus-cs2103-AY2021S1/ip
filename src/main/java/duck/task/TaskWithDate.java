package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more specific class of Task that has a date to specify a due date.
 */
public abstract class TaskWithDate extends Task {
    private LocalDate date;

    /**
     * Initializes with an additional date on top of the Task initialization.
     *
     * @param description Description of task.
     * @param date Due date of task.
     */
    public TaskWithDate(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the formatted date as a string.
     *
     * @return Formatted date.
     */
    public String getDateAsString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

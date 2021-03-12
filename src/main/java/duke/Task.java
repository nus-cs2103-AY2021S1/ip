package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class stores information about a particular task.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String symbol;
    protected String by;
    protected LocalDate date;
    protected LocalDateTime dateTime;

    /**
     * Constructor which takes in a description of the task name.
     *
     * @param description name of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        by = "";
        date = null;
        dateTime = null;
    }

    /**
     * Returns the status of the task, done or undone.
     *
     * @return the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     *
     * @return this task
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Returns the name or description of the task.
     *
     * @return the name or description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of task in for form of a modifier or symbol.
     *
     * @return the type of task in for form of a modifier or symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the date of the task requirements, depending on the type of task.
     *
     * @return the date of the task requirements, depending on the type of task
     */
    public String getDate() {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a"));
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return by;
        }
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + description);
    }
}

package duke.task;

import java.time.LocalDate;

/**
 * Represents task of user
 */
public class Task {
    protected String description;
    protected boolean isDone;
    String dateString;
    LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateString = "0001-01-01";
        this.date = LocalDate.parse("0001-01-01");
    }

    /**
     * Returns status icon of task object according to its done status
     * @return status icon of task object
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns date of task object
     * @return date of task object
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the value of isDone variable to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of task object to be stored in file
     * @return string representation of task object for file storage
     */
    public String toFileString() {
        return "Error";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

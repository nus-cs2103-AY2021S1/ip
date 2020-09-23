package chatbot.data;

import java.time.LocalDate;

/**
 * Represents a task (e.g todo, deadline, event) with a description, type, completion status and date.
 */

public class Task {
    protected final String description;
    protected final String type;
    protected final boolean isDone;
    protected final LocalDate date;

    protected Task(String description, String type, boolean isDone, LocalDate date) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
        this.date = date;
    }

    /**
     * Returns a description of the task.
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task ("1" - complete, "0" - incomplete).
     * @return task status
     */
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns the status of the task as symbol (tick or cross).
     * @return task status
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a date, of the format (yyyy-mm-dd), associated with the task.
     * @return the date of the task
     */
    public LocalDate getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Marks the task as done. A new copy of the object is returned.
     * @return a task object marked as done
     */
    public Task markDone() {
        return new Task(this.description, this.type, true, this.date);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "]"
                + " " + this.description;
    }
}

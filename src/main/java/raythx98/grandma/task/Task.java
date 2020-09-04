package raythx98.grandma.task;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public class Task {

    public static final String tick = "\u2713";
    public static final String cross = "\u2718";

    protected String description;
    protected boolean isDone;
    protected String tag = "";

    /**
     * Something.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return isDone ? tick : cross;
    }

    public int getStatusBinary() {
        return isDone ? 1 : 0;
    }

    public String getTaskType() {
        return tag;
    }

    /**
     * Marks the task as done, and returns the String representation of the task.
     *
     * @return a String representation of the task.
     */
    public String markAsDone() {
        isDone = true;
        return toString();
    }

    public String toPrint() {
        return tag + "|" + getStatusBinary() + "|" + getDescription();
    }

    /**
     * Something.
     *
     * @return
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

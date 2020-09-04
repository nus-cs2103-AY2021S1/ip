package raythx98.grandma.task;

import raythx98.grandma.exception.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {

    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";
    public static final int TICK_BINARY = 1;
    public static final int CROSS_BINARY = 1;
    public static final String TODO_TAG = "T";
    public static final String DEADLINE_TAG = "D";
    public static final String EVENT_TAG = "E";
    public static final String TIME_FORMAT = "HHmm";
    public static final String DATE_FORMAT = "DDmmyy";
    public static final String OUTPUT_TIME_FORMAT = "h:mm a";
    public static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy";

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
        return isDone ? TICK : CROSS;
    }

    public int getStatusBinary() {
        return isDone ? TICK_BINARY : CROSS_BINARY;
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

    public String toPrint() throws DukeException {
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

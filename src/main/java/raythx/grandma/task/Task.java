package raythx.grandma.task;

import raythx.grandma.exception.DukeException;

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
    public static final String DATE_FORMAT = "ddMMyy";
    public static final String OUTPUT_TIME_FORMAT = "h:mm a";
    public static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy";

    protected String description;
    protected boolean isDone;
    protected String tag = "";
    protected String taskTag;

    /**
     * Constructor for tasks.
     *
     * @param description task description.
     */
    public Task(String description, String taskTag) {
        this.description = description;
        this.isDone = false;
        this.taskTag = taskTag;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return isDone ? TICK : CROSS;
    }

    public int getBinaryStatus() {
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

    public String encodeTask() throws DukeException {
        return tag + "|" + getBinaryStatus() + "|" + getDescription() + "|" + getTaskTag();
    }

    /**
     * String representation of the task.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " #" + getTaskTag();
    }
}

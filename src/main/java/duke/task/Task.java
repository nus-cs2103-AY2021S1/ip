package duke.task;

/**
 * A generic task that contains a description and can be either done or not done.
 */
public abstract class Task {

    /** Symbols used to indicate whether the task is done or not. */
    private static final String SYMBOL_TICK = "\u2713";
    private static final String SYMBOL_CROSS = "\u2718";

    /** A description of the task. */
    protected String description;

    /** A boolean indicating whether the task is done or not. */
    protected boolean isDone;

    /**
     * Constructs a generic task with a description and defaults to be not done.
     * (For invocation by subclass constructors.)
     *
     * @param description The task description
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as done. */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the icon symbolizing whether the task is done or not done.
     *
     * @return A symbol as a String
     */
    public String getStatusIcon() {
        if (isDone) {
            return SYMBOL_TICK;
        } else {
            return SYMBOL_CROSS;
        }
    }

    /**
     * Gets the type of the task represented by a unique identifier.
     *
     * @return The type of the task as its unique identifier
     */
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the String representation of the task customized for being written to a file.
     *
     * @return The task as a string formatted for being written to the file
     */
    public String toFileString() {
        int stat = 0;
        if (isDone) {
            stat = 1;
        }
        return String.format("%s | %d | %s", getType(), stat, description);
    }
}

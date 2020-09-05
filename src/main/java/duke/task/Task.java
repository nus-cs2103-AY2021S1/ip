package duke.task;

/**
 * A generic task that contains a description and can be either done or not done.
 */
public abstract class Task {

    /** A description of the task. */
    protected String description;
    /** A boolean indicating whether the task is done or not. */
    protected boolean isDone;

    /**
     * Constructs a generic task with a description and defaults to be not done.
     * (For invocation by subclass constructors.)
     * @param description The task description
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the icon symbolizing whether the task is done or not done.
     * @return A symbol as a String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Gets the type of the task represented by a unique identifier.
     * @return The type of the task as its unique identifier
     */
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the String representation of the task customized for being written to a file.
     * @return The task as a string formatted for being written to the file
     */
    public String toFileString() {
        int stat;
        if (this.isDone) {
            stat = 1;
        } else {
            stat = 0;
        }
        return String.format("%s | %d | %s", this.getType(), stat, this.description);
    }
}

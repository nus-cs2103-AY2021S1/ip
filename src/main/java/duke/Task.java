package duke;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructor that initializes with description.
     *
     * @param description task info.
     */
    protected Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done, and returns this task.
     *
     * @return this Task object.
     */
    protected Task setDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Returns a text representation of object.
     * This is for purpose of storage in .txt file.
     *
     * @return String of .txt format
     */
    protected String getTxtFormat() {
        return (this.isDone ? "1, " : "0, ") + this.description;
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        String status = (this.isDone) ? "[✓]" : "[✗]";
        return status + " " + description;
    }

    public String getDescription() {
        return this.description;
    }

}

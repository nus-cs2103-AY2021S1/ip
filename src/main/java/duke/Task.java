package duke;

/**
 * Abstract class representing a task.
 */
public abstract class Task {
    protected String description;
    protected boolean done = false;

    /**
     * Constructor that initializes with description.
     *
     * @param description task info.
     */
    protected Task(String description) {
        this.description = description;
    }

    private boolean isDone() {
        return this.done;
    }

    /**
     * Marks this task as done, and returns this task.
     *
     * @return this Task object.
     */
    protected Task markAsDone() {
        this.done = true;
        return this;
    }

    /**
     * Returns a text representation of object.
     * This is for purpose of storage in .txt file.
     *
     * @return String of .txt format
     */
    protected String textFormat() {
        return (this.done ? "1, " : "0, ") + this.description;
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        String status = this.done ? "[✓]" : "[✗]";
        return status + " " + description;
    }

}

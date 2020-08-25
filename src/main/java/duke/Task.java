package duke;

/**
 * Represents a Task object
 */
public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
            return isDone ? "X" : " ";
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * String representation of Task object
     * @return String representation of Task object
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * String representation of Task object to be saved to hard disk
     * @return String representation of Task object to be saved to hard disk
     */
    public String fileFormat() {
        String indicateDone = isDone ? "1" : "0";
        return indicateDone + " , " + description + " , ";
    }
}

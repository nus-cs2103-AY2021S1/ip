package duke.task;

/**
 * Represents a Task which have 3 child class: ToDo, Event and Deadline.
 */
public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructor for Task
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Constructor for Task
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toStoreFormat() {
        return "";
    }
}

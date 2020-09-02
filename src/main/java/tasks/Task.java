package tasks;

/**
 * Represents a general Task object with a name
 */

public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Creates a new Task object.
     * @param name Name of the task.
     * @param isDone Whether the task is done or not.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Prints a tick or X symbol.
     * @return a tick or X symbol.
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    public String writeString() {
        return (isDone ? "1" : "0") + " # " + name;
    }
}

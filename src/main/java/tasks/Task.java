package tasks;

/**
 * Represents a general Task object with a name
 */

public class Task {
    private String name;
    private boolean done;

    /**
     * Creates a new Task object.
     * @param name Name of the task.
     * @param done Whether the task is done or not.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Prints a tick or X symbol.
     * @return a tick or X symbol.
     */

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    public String writeString() {
        return (done ? "1" : "0") + " # " + name;
    }
}

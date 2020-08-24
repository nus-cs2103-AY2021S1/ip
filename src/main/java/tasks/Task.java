package tasks;

/**
 * Represents a general tasks.Task object
 */

public class Task {
    private String name;
    private boolean done;

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    public String writeString() {
        return (done ? "1" : "0") + " # " + name;
    }
}

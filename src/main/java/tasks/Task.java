package tasks;

/**
 * Represents a general Task object with a name
 */

public class Task {
    protected String name;
    protected boolean done;

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

    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    public String writeString() {
        return (done ? "1" : "0") + " # " + name;
    }
}

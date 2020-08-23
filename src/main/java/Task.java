/**
 * Encapsulates a general Task object
 */

public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}

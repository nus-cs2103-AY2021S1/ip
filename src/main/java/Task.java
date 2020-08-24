/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    char type;
    String unparseMessage;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(boolean done, String description, char type) {
        this.isDone = done;
        this.description = description;
        this.type = type;
    }

    /**
     * Tells whether the task is done.
     *
     * @return An icon, either a tick or a cross, representing whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the status of the task to done.
     */
    public void done() {
        this.isDone = true;
        int indexOf0 = unparseMessage.indexOf('0');
        unparseMessage = unparseMessage.substring(0, indexOf0) + "1" + unparseMessage.substring(indexOf0+1);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

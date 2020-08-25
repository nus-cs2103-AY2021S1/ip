package dukeclass;

/**
 * Represents a task.
 * The task has a String message and a Boolean status.
 */
public abstract class Task {

    public static String icon;
    protected String taskString;
    protected Boolean status;

    public Task(String taskString) {
        this.taskString = taskString;
        this.status = false;
    }

    public void setStatus(Boolean b) {
        this.status = b;
    }

    public String toString() {
        return this.taskString;
    }

    public abstract String toDataString();

    public boolean getStatus() {
        return this.status;
    }
}

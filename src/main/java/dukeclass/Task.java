package dukeclass;

/**
 * Represents a task.
 * The task has a String message and a Boolean status.
 */
public abstract class Task {

    protected String taskString;
    protected Boolean status;

    /**
     * Constructor for Task.
     * Status is set to false.
     *
     * @param taskString  the task given by the user
     */
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

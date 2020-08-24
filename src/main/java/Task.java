/**
 * Represents an abstract task object with basic task properties.
 */
public abstract class Task {

    private boolean done = false;

    abstract protected String getTask();

    /**
     * Marks the task object as done.
     */
    protected void markDone() {
        this.done = true;
    }

    /**
     * Checks if the task is done.
     */
    protected boolean isDone() {
        return this.done;
    }

}

/**
 * Represents an abstract task object with basic task properties.
 */
public abstract class Task {

    private boolean isDone = false;

    abstract protected String getTask();

    /**
     * Marks the task object as done.
     */
    protected void markDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task is done.
     */
    protected boolean isDone() {
        return this.isDone;
    }

}


package luoyi.duke.data.task;

/**
 * Immutable Task Object.
 * A task has a description and a completion status.
 */
public abstract class Task implements ITask {
    protected final String description;
    protected final boolean isDone;

    /**
     * Returns a new Task object.
     * @param description Description of the task.
     * @param isDone Boolean to keep track of whether the task is done.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "\u2713" : "\u2718", description);
    }
}

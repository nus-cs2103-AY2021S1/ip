package duke.task;

/**
 * Represents a task.
 * @author Tee Kok Siang
 */
public abstract class Task {
    /** Description of the task */
    protected String description;
    /** Indicates if task is done */
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns formatted task information.
     * It will be used to write into the file.
     * @return Formatted task information.
     */
    public abstract String toFileString();

    /**
     * Returns task description
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if task is done
     * @return task isDone
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
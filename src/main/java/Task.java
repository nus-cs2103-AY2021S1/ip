import java.time.LocalDateTime;

/**
 * Take each user input as Task.
 */
public class Task implements DueDateTime {
    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor specifying a description of the task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task Constructor specifying boolean isDone and description of the task.
     *
     * @param isDone boolean
     * @param description String
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Return the status of the Task.
     * If the task is done, return ✓; If the task is not done, return ✗.
     *
     * @return String ✓ or ✗ symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return Task to String.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDateTime() {
        return LocalDateTime.MAX;
    }
}

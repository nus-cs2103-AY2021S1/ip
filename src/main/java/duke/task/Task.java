package duke.task;

/**
 * Task Class handles tasks that the user specifies.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor to make a task object.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns an icon indicating whether the task is complete.
     * @return the icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the task description.
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a boolean indicating whether the task is complete
     * @return a boolean indicating whether the task is complete
     */
    public Boolean getDone() {
        return isDone;
    }

    /**
     * Returns a string indicating whether the task is complete along with the description of the task.
     * @return icon and description of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

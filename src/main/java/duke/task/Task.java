package duke.task;

/**
 * Represents a Task created by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for a Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbolic representation of the completion status of the task.
     *
     * @return Tick if task is completed, cross if task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the String representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the text representation of a Task to be saved in a text file.
     * Default Method to be overridden by classes which inherit Task.
     *
     * @return Text representation of Task.
     */
    public String toData() {
        return "";
    }
}

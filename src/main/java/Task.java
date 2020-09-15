import java.time.LocalDate;

/**
 * Class to represent a Task object.
 * @author vanGoghhh
 */

public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param description description of the task object.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return icon to indicate whether a task is done.
     * @return a "check" or a "cross" depending on whether the task is done.
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Get the status of a task.
     * @return boolean
     */
    protected boolean getStatus() {
        return this.isDone;
    }

    /**
     * Get task status in the form of an integer.
     * @return 1 or 0 depending on status on task.
     */
    protected int getTaskStatus() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns description of the task.
     * @return description of the task.
     */
    protected String getDescription() {
        return this.description;
    }

    abstract protected LocalDate getTaskDeadline();

    abstract protected Task updateTaskDescription(String newDescription);

    abstract protected Task updateTimedTaskDeadline(LocalDate newDueDate);

    abstract protected String inputInFile();

    /**
     * Prints the task object.
     * @return string representation of a task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

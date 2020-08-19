/**
 * The Task class encapsulates tasks which are pieces of work
 * to be done. This is used by the Duke chatbot for creating
 * of tasks which can be marked as completed.
 */

public class Task {

    /** The description of the task */
    protected String description;

    /** Flags if a task has been completed */
    protected boolean isDone;

    /**
     * Constructor for the Task.
     * Tasks are created to be not completed.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the completion flag of the task and generates the
     * unicode string representation of the task status;
     * a tick for completed, a cross for not completed.
     * @return the unicode of the completion flag.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Checks if the task is completed based on the completion flag.
     * @return boolean value for completion flag.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Marks a task as completed by setting completion flag to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the string description of the task, which is the same
     * string used during initialization.
      * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

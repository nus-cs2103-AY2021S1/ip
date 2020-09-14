package duke.task;

import duke.exception.DoneException;

/**
 * Represents a task object
 * A <code>Task</code> object corresponds to a task created using the command
 * given by the user such as "todo", "event", "deadline".
 */
public abstract class Task {

    protected String description;
    protected int status;

    /**
     * Constructor of Task Class
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.status = 0;
    }

    /**
     * Constructor for Task Class
     *
     * @param description description of the task
     * @param status status of the task
     */
    public Task(String description, int status) {
        this.description = description;
        this.status = status;
    }

    /**
     * Returns the an icon depending on status
     *
     * @return an icon
     */
    public String getStatusIcon() {
        return (status == 1 ? "\u2713" : "\u2718");
    }

    /**
     *
     */
    public void markAsDone() throws DoneException {
        if (status == 1) {
            throw new DoneException("Task is already completed. Please re-enter:");
        } else {
            status = 1;
        }
    }

    /**
     * Gets the current status of the task
     *
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns a string to be written inside the text file
     *
     * @param status current status of the task
     * @return string representation of the task
     */
    public abstract String saveText(int status);

    /**
     * Returns a string representation of a task object
     *
     * @return string representation of a task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

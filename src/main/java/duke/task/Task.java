package duke.task;

/**
 * Task is used to store information about the Task that must done.
 * It can also tell us if it has been done or not.
 */
public class Task {

    /** Description of Task. */
    protected String description;
    /** Status of Task. */
    protected boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if this Task is done or not.
     *
     * @return true if this Task is done, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the indicator based on the status of the task.
     *
     * @return a "V" if it is done, otherwise "X"
     */
    public String getIcon() {
        if (isDone) {
            return "V";
        } else {
            return "X";
        }
    }

    /**
     * Gets information about this task except the status of this task (done or not done).
     *
     * @return a String array that contains the description of this Task.
     */
    public String[] getInfo() {
        return new String[] {description};
    }

    /**
     * Returns String representation of this Task.
     *
     * @return String that represent this Task.
     */
    @Override
    public String toString() {
        return "[" + getIcon() + "] " + description;
    }
}

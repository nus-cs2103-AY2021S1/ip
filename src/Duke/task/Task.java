package Duke.task;

/**
 * This class simulates the tasks
 * that the users give to Duke.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Initialize a Task object.
     * @param description A string describing
     *                    the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return tick when the task is done,
     * cross when the task is not done.
     *
     * @return A string showing a tick or
     *         cross as described above.
     */
    public String getStatusIcon() {
        return isDone
                ? "[" + "\u2713" + "]"
                : "[" + "\u2718" + "]";
    }

    /**
     * This method is to set the task
     * as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " "
                + this.description;
    }
}

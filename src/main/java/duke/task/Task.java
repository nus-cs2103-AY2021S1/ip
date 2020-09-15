package duke.task;

import duke.tools.FormatString;

/**
 * Simulates the tasks that the users give to Duke.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description A string describing
     *                    the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick when the task is done,
     * cross when the task is not done.
     *
     * @return A string showing a tick or
     *         cross as described above.
     */
    public String getStatusIcon() {
        return isDone
                ? "[" + FormatString.TICK.toString() + "]"
                : "[" + FormatString.CROSS.toString() + "]";
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns Task with new content.
     *
     * @param newContent Content that the user wants to change to.
     * @return Task with new content.
     */
    public Task updateDetail(String newContent) {
        Task result = new Task(newContent);

        if (isDone) {
            result.setDone();
        }

        return result;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}

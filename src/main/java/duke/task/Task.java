package main.java.duke.task;

import main.java.duke.main.FormatString;

/**
 * This class simulates the tasks
 * that the users give to Duke.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Initializes a Task object.
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
     * Returns the description of a Task.
     *
     * @return The description of a Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task as done.
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

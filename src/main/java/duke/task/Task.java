package duke.task;

import java.util.Date;

/**
 * Abstract class that models a task.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected Date date;

    /**
     * Constructs a Task.
     *
     * @param description The description of the task.
     */
    public Task(String description, Date date) {
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    /**
     * Returns icon representing whether the task is done or not.
     *
     * @return Icon representing whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task to be done.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the task for saving.
     *
     * @return String representation of the task for saving purposes.
     */
    public abstract String toSaveFormat();

    /**
     * Returns true if the task contains the description.
     *
     * @param givenDescription The given description.
     * @return True if the task contains the description, false otherwise.
     */
    public boolean contains(String givenDescription) {
        return this.description.contains(givenDescription);
    }

    /**
     * Compares 2 tasks by date.
     *
     * @param other The other Task to compare.
     * @return int corresponding to the order.
     */
    @Override
    public int compareTo(Task other) {
        if (this.date == null && other.date == null) {
            return 0;
        } else if (this.date != null && other.date == null) {
            return -1;
        } else if (this.date == null && other.date != null) {
            return 1;
        } else {
            return this.date.compareTo(other.date);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}

package tasks;

/**
 * Parent class for all Tasks used in Duke.
 */
public class Task {
    /** Checks if the Task has been completed. */
    private boolean isDone;

    /** A description of the Task. */
    private final String description;

    /**
     * Creates a Task.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        this.isDone = false; // new tasks are not done
        this.description = name;
    }

    /**
     * Returns the description of the Task.
     *
     * @return description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a Tasks as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Checks if a tasks is completed.
     *
     * @return true if completed and vice-versa.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * ToString method to display the tasks in.
     */
    public String toString() {
        if (isDone()) {
            return "[Completed] " + this.description;
        } else {
            return "[Pending] " + this.description;
        }
    }

    /**
     * Dummy method for Deadline and Event to write to database.
     * The issue was that toString could not be used.
     *
     * @return NIL.
     */
    public String toWrite() {
        return "";
    }
}

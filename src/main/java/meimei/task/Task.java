package meimei.task;

/**
 * Represents a task that has a name <code>taskName</code> and a status <code>isDone</code>.
 * Adapted from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html
 */

public class Task {
    /** Name of the task */
    protected final String taskName;
    /** Status of the task */
    protected boolean isDone;

    /**
     * Public constructor. A task is <b>not done</b> by default when first added.
     *
     * @param taskName Name of the task as given by user.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the name of the task given by the user.
     *
     * @return Name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or cross symbols
    }

    /**
     * Returns a boolean value representing whether the task has been
     * completed or not. A true value indicates that the task has been completed.
     *
     * @return Boolean indicating whether task has been done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks a task as having been completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }
}

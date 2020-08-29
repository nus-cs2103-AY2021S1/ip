package duke;

/**
 * Abstract class to represent a task.
 * Contains task name and completed status.
 *
 * @author YanCheng
 */

public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param taskName Name of task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void completed() {
        isDone = true;
    }

    /**
     * Return information of the task to be stored locally.
     * @return Task information
     */
    public abstract String getInfo();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "\u2713" : "\u2718", taskName);
    }
}

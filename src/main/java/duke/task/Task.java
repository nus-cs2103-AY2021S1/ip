package duke.task;

/**
 * Represents a Task to allow different types of tasks to inherit.
 */
public class Task {
    /**
     * Description of task.
     */
    protected String taskName;
    /**
     * Checks if task is completed or not.
     */
    protected boolean isDone;
    /**
     * '0' to represent incomplete, '1' to represent completed.
     */
    protected String completed;

    /**
     * Creates a Task with a given task name, with it being not done yet.
     * @param taskName Description of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.completed = "0";
    }

    /**
     * Returns a tick or a cross based on whether the task is done or not done.
     * @return Tick or cross symbols.
     */
    public String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        this.completed = "1";
    }

    /**
     * Converts Task to string form to be saved in user's files.
     * @return Empty string.
     */
    public String taskToText() {
        return "";
    }

    /**
     * Returns string format of Task.
     * @return A string representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}

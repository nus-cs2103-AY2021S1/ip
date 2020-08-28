/**
 * Encapsulates a task for the user
 */
public class Task {
    private Boolean done;
    private String taskName;
    private TaskType type;

    public Task(String taskName, TaskType type) {
        this.done = false;
        this.taskName = taskName;
        this.type = type;
    }

    /**
     * Marks current task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Returns a String symbol representing the completion of the task
     * @return a String symbol representing the completion of the task
     */
    public String getTaskStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    /**
     * Returns a String symbol representing the type of task
     * @return string symbol representing the type of task
     */
    public String getType() {
        return this.type.toString();
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}

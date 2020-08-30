/**
 * Encapsulates a task for the user
 */
public class Task {
    private Boolean isDone;
    private final String taskName;
    private final TaskType type;

    /**
     * Creates a Task instance
     * @param taskName task description
     * @param type type of task
     */
    public Task(String taskName, TaskType type) {
        this.isDone = false;
        this.taskName = taskName;
        this.type = type;
    }

    /**
     * Marks current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String symbol representing the completion of the task
     * @return a String symbol representing the completion of the task
     */
    public String getTaskStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
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

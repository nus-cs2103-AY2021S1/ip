package duke.task;

/**
 * Encapsulates a duke.task for the user
 */
public class Task {
    private Boolean isDone;
    private final String taskName;
    private final TaskType type;

    /**
     * Creates a duke.task.Task instance
     * @param taskName duke.task description
     * @param type type of duke.task
     */
    public Task(String taskName, TaskType type) {
        this.isDone = false;
        this.taskName = taskName;
        this.type = type;
    }

    /**
     * Marks current duke.task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String symbol representing the completion of the duke.task
     * @return a String symbol representing the completion of the duke.task
     */
    public String getTaskStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns a String symbol representing the type of duke.task
     * @return string symbol representing the type of duke.task
     */
    public String getType() {
        return this.type.toString();
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}

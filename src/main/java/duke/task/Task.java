package duke.task;

/**
 * Encapsulates a task for the user
 */
public class Task {
    private Boolean isDone;
    private final String taskDetails;
    private final TaskType type;
    private Priority priority;

    /**
     * Creates a duke.task.Task instance
     * @param taskName duke.task description
     * @param type type of duke.task
     */
    public Task(String taskName, TaskType type) {
        this.isDone = false;
        this.taskDetails = taskName;
        this.type = type;
        this.priority = Priority.LOW;
    }

    /**
     * Creates a duke.task.Task instance with priority level.
     * @param taskName The duke.task description.
     * @param type Type of duke.task.
     * @param priority Task priority level.
     */
    public Task(String taskName, TaskType type, Priority priority) {
        this.isDone = false;
        this.taskDetails = taskName;
        this.type = type;
        this.priority = priority;
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
        assert this.priority != null : "Priority not found!";
        return "[" + priority.toString() + "] " + this.taskDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;

        /*return (this.priority == task.priority) && (this.taskDetails == task.taskDetails)
                && (this.type == task.type) && (this.isDone == task.isDone);*/
        return (this.priority.equals(task.priority)) && (this.taskDetails.equals(task.taskDetails))
                && (this.type.equals(task.type)) && (this.isDone.equals(task.isDone));
    }
}

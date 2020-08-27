package duke;

/**
 * Handles all kinds of tasks.
 */
public class Task {
    protected String taskTitle;
    protected boolean isDone;
    protected TaskTypes taskType;

    /**
     * Constructor of Task class.
     *
     * @param taskTitle A string of task name.
     * @param isDone Status of the task.
     * @param taskType Type of the task.
     */
    public Task(String taskTitle, Boolean isDone, TaskTypes taskType) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the status of the task ("✓" or "✗").
     * @return The icon representing the status of the task.
     */
    public String getStatus() {
        return (isDone
                ? "✓"
                : "✗");
    }

    /**
     * Get the status of the task ("1" means done / "0" means not donw).
     * @return The int representing the status of the task
     */
    public String getStatusNum() {
        return (isDone
                ? "1"
                : "0");
    }

    /**
     *  Returns a string of the task.
     * @return A string of the task.
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatus() + "] " + taskTitle;
    }

    /**
     * Returns a string follows the format of the file.
     * @return A string follows the format of the file.
     */
    public String writeToFile() {
        return this.taskType + " | " + getStatusNum() + " | " + taskTitle;
    }
}

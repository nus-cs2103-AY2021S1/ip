package duke.task;

/**
 * Represents an upcoming task in to-do list.
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Construct a task item.
     * @param taskDescription title of the task.
     * @param isDone status of the task.
     */
    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Outputs the description of the task.
     * @return the description of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Gets the corresponding symbol based on the status of task.
     * @return symbol corresponding to the status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.getTaskDescription();
    }

    /**
     * Represents the string written into data.txt.
     * @return A string written into the data.txt.
     */
    public String toWrite() {
        return "T | " + (this.isDone ? '1' : '0') + " " + "| " + this.taskDescription;
    }
}

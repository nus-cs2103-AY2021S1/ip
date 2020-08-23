package duke.task;

import java.util.Optional;

/**
 * Represents the Task object.
 */
public abstract class Task {
    protected final String description;
    protected boolean isCompleted;

    protected static String TICK = "\u2713";
    protected static String CROSS = "\u2717";

    /**
     * Constructor method of Task.
     * @param description the description of the Task.
     * @param isCompleted indicates if the Task has been completed.
     */
    Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Converts the Task object to a String.
     * Indicates the completion status of the Task and its description.
     * @return a String representing the Task object.
     */
    public String toStringSuffix() {
        String symbol = this.isCompleted ? TICK : CROSS;
        return String.format("[%s] %s", symbol, this.description);
    }

    protected boolean isEqual(Task task) {
        return this.description.equals(task.getTaskDescription()) &&
                this.isCompleted == task.isTaskCompleted();
    }

    /**
     * Changes the completion status of Task to completed.
     * This is a mutable operation.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Gets the description of the Task.
     * @return the String of the Task description.
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Gets the completion status of the Task.
     * @return true if the Task is completed and false if not.
     */
    public boolean isTaskCompleted() {
        return this.isCompleted;
    }

    /**
     * Gets the task symbol of the subtype of Task.
     * @return the task symbol String.
     */
    public abstract String getTaskSymbol();

    /**
     * Gets the formatted String of the Datetime object of the Task.
     * @return an Optional object containing the formatted Datetime String.
     */
    public abstract Optional<String> getTaskDatetime();
}

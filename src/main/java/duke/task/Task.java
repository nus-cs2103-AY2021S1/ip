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
     * Constructor method of <code>Task</code>.
     * @param description the description of the <code>Task</code>.
     * @param isCompleted indicates if the <code>Task</code> has been completed.
     */
    Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Converts the <code>Task</code> to a <code>String</code>.
     * Indicates the completion status of the <code>Task</code> and its description.
     * @return a <code>String</code> representing the <code>Task</code>.
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
     * Changes the completion status of <code>Task</code> to completed.
     * This is a mutable operation.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Gets the description of the <code>Task</code>.
     * @return the <code>String</code> of the <code>Task</code> description.
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Gets the completion status of the <code>Task</code>.
     * @return <code>true</code> if the <code>Task</code> is completed
     * and <code>false</code> if not.
     */
    public boolean isTaskCompleted() {
        return this.isCompleted;
    }

    /**
     * Gets the task symbol of the subtype of <code>Task</code>.
     * @return the task symbol <code>String</code>.
     */
    public abstract String getTaskSymbol();

    /**
     * Gets the formatted <code>String</code> of the <code>Datetime</code> of the <code>Task</code>.
     * @return an <code>Optional</code> containing the formatted <code>Datetime String</code>.
     */
    public abstract Optional<String> getTaskDatetime();
}

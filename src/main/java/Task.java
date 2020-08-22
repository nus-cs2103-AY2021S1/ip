/**
 * Represents a task.
 * @author Lim Zi Yang
 */
public class Task {
    protected final String description;
    protected final boolean isDone;

    /**
     * Creates an undone task.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task.
     * @param description Description of task.
     * @param isDone Whether the task is done.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Description getter.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * isDone getter.
     * @return Boolean value representing whether the task is done.
     */
    public boolean checkIsDone() {
        return isDone;
    }

    /**
     * Generates symbol based on whether task is done.
     * @return A String representing a tick or cross symbol.
     */
    protected String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     * @return A done task.
     */
    public Task markDone() {
        return new Task(getDescription(), true);
    }

    /**
     * Convert to string value of task to be stored as data.
     * @return String to be stored in hard disk.
     */
    public String convertToStringData() {
        return checkIsDone()
                ? "T/1/" + getDescription()
                : "T/0/" + getDescription();
    }

    /**
     * Overridden toString method.
     * @return String value of the task.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }

}

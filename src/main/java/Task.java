/**
 * Represents a task.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Task {
    /**
     * Task description.
     */
    private String description;
    /**
     * This states whether task is done.
     */
    private boolean isDone;

    /**
     * Task constructor, marked as not done.
     * @param description The description of the task.
     */
    public Task(final String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task constructor, marked as done.
     * @param description The description of the task.
     * @param isDone    Whether the task is done.
     */
    public Task(final String description, final boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * This method marks the task as done.
     * @return This returns the same task that has been marked as done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "["
                + this.getStatusIcon() + "] "
                + this.description;
    }

    /**
     * This method formats the task for display to the user.
     * @return This returns a string containing the task details.
     */
    public String display() {
        return "";
    }
}

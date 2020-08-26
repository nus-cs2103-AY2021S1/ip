/**
 * Represents a task.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
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
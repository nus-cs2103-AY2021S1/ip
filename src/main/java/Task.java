/**
 * Task class that contains values and details about each individual task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object
     *
     * @param description details about the Task
     * @return Task with a corresponding description and sets it as uncompleted.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns either ✓ or ✘, depending on whether task has been done.
     *
     * @return ✓ or ✘.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /**
     * Marks the task as done, by changing its isDone boolean.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns task description and its status icon.
     *
     * @return string containg its description and its status icon.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
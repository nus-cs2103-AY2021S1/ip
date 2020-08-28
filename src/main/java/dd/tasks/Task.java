package dd.tasks;

/**
 * A task stored in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class Constructor.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Converts task into string to be written into file when saving tasks.
     *
     * @return String to be written into file.
     */
    public String saveString() {
        return "";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

/**
 * Represents a task. A task has a description of the activity, and an indicator
 * of whether the activity has been completed. A task must be a todo, deadline,
 * or event.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description. The Task is marked
     * as not done by default.
     * @param description Description of the activity associated with the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Indicates whether the Task is done.
     * @return String object indicating whether the Task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns an alternate representation of the Task that will be saved in
     * the hard disk.
     * @return String object representing the Task.
     */
    public abstract String getFormattedString();
}

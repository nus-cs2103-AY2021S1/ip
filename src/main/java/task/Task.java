package task;

/**
 * Represents a Task, providing implementation of a general task. 
 */
public class Task {
    /** Description of the task. */
    protected String description;

    /** State of whether the task is done. */
    protected boolean isDone;

    /**
     * Creates a task.
     *
     * @param description Description of the task.
     * @param isDone State of whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a tick icon or cross icon based on done state of the task.
     *
     * @return Tick icon or cross icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return a string representation of the task.
     * 
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

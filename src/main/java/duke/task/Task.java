package duke.task;

/**
 * Encapsulates a Task.
 * May be a ToDo, Event, or Deadline.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task based on the description and marks it as not done.
     *
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     *
     * @return Indicator if the task is already completed.
     */
    public boolean complete() {
        if (this.isDone) {
            return true;
        } else {
            this.isDone = true;
            return false;
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Expresses the task as a string which is used for storage purposes.
     *
     * @return A string representing the task.
     */
    public String store() {
        return this.isDone + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

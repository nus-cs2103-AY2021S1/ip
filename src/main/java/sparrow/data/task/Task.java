package sparrow.data.task;

/**
 * Represents a Task in the task list. Child classes include Todo, Deadline and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object with the given description.
     * @param description Details of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Task
                && description.equals(((Task) other).description));
    }
}

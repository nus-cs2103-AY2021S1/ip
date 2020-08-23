package main.java.duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new instance of a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets task completion symbol.
     *
     * @return Tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks task as completed.
     */
    public void doneTask() {
        this.isDone = true;
    }

    /**
     * Marks task as uncompleted.
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Gets description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if task is done.
     *
     * @return Boolean value.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Converts a Task object to a string.
     *
     * @return A string displaying the task and its status.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

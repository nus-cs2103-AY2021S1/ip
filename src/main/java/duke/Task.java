package duke;

// Group each action the user input as a task, with the description and status if the task is done
public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this(description, false);
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Get status icon for the current task, tick = done, cross = no done
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Return updated task
     *
     * @param isDone New status for the task
     * @return new task with updated status
     */
    public Task updateStatus(boolean isDone) {
        return new Task(description, isDone);
    }

    // Print the status of the task before the task description
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

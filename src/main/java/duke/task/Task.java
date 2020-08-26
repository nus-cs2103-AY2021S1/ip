package duke.task;

/**
 * Task is an abstract class that encapsulates a task to be completed by the user.
 * A task is represented by a string description.
 * The completion of a task is represented by a boolean.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done by setting the boolean isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of the task that is recognised by the Storage class.
     * It is used to represent a task in the database.
     * @return String representation of the task.
     */
    public String toData() {
        int binary = isDone ? 1 : 0;
        return binary + " | " + description;
    }
    
    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s] %s", statusIcon, description);
    }
}
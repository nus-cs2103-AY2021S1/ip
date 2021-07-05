package duke;

/**
 * Represents a task given by the user, containing the name and the completion status of the task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    
    /**
     * Creates a new task with the specified description.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the string of the completion status of the task.
     * 
     * @return A string of the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Changes the completion status of the task to be done.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Gets the string of the name of the task
     * 
     * @return A string of the name of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the Task.
     * 
     * @return A String containing the description and completion status of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a string representation of the Task stored in the storage.
     * 
     * @return A String representing the code of the task stored in the storage.
     */
    public abstract String encode();
}

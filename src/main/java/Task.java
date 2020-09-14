/**
 * A task that comprises a task description and whether it is done.
 */
public class Task {
    /** The task's messages */
    protected String description;

    /** Whether the task is Done*/
    protected boolean isDone;

    /**
     * Create a task.
     * @param description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create a task.
     * @param description of task.
     * @param isDone done status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status Icon.
     * @return tick if the task is done, and else a X.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the task message.
     * @return description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the done status of the task.
     * @return true if the task is done, else false.
     */
    public boolean checkDone(){
        return this.isDone;
    }

    /**
     * Make the task done.
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Returns the String description for task.
     * @return String description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

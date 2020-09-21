package duke;

/**
 * Represents a <code>Task</code> object.
 */
public class Task {
    /**
     * The command of a task.
     */
    protected String command;
    /**
     * Indicates whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * Creates a new <code>Task</code> with the given <code>command</code>.
     * Initially the task is not done.
     */
    public Task(String command){
        this.command = command;
        this.isDone = false;
    }

    /**
     * Returns the command of a task.
     * @return the command of this task.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the status of a task.
     * @return true if this task is done, false if this task is not done yet.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets this task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of a task.
     * @return the string representation of this task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713]" + this.command;
        } else {
            return "[\u2718]" + this.command;
        }
    }
}
